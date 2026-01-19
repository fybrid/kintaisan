package com.kyosaka.kintaisan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.kyosaka.kintaisan.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kyosaka.kintaisan.dto.UserAccountCreateRequest;
import com.kyosaka.kintaisan.dto.UserAccountUpdateRequest;
import com.kyosaka.kintaisan.dto.UserListRequest;
import com.kyosaka.kintaisan.entity.departments;
import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.entity.UserProfile;
import com.kyosaka.kintaisan.repository.UserAccountRepository;
import com.kyosaka.kintaisan.repository.departmentsRepository;
import com.kyosaka.kintaisan.repository.workplacesRepository;
import com.kyosaka.kintaisan.entity.workplaces;

@Service
public class UserAccountService {

  private final UserAccountRepository userAccountRepository;
  private final UserProfileRepository userProfileRepository;
  private final departmentsRepository departmentsRepository;
  private final workplacesRepository workplacesRepository;
  private final PasswordEncoder passwordEncoder;
  private static final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

  public UserAccountService(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository, UserProfileRepository userProfileRepository, departmentsRepository departmentsRepository, workplacesRepository workplacesRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userAccountRepository = userAccountRepository;
    this.userProfileRepository = userProfileRepository;
    this.departmentsRepository = departmentsRepository;
    this.workplacesRepository = workplacesRepository;
  }

  public enum SigninStatus {
    SUCCESS,
    BAD_REQUEST,
    USER_NOT_FOUND,
    PASSWORD_MISMATCH
  }

  public record SigninResult(SigninStatus status, String userId, Short roleId) {}

  // ログインメソッド
  public SigninResult signin(String username, String password) {
    if (username == null || password == null || username.isBlank() || password.isBlank()) {
      return new SigninResult(SigninStatus.BAD_REQUEST, null, null);
    }

    Optional<UserAccount> userOpt = userAccountRepository.findByUserIdOrName(username, username);
    if (userOpt.isEmpty()) {
      return new SigninResult(SigninStatus.USER_NOT_FOUND, null, null);
    }

    UserAccount user = userOpt.get();
    if (passwordEncoder.matches(password, user.getPassword())) {
      return new SigninResult(SigninStatus.SUCCESS, user.getUserId(), user.getRoleId());
    }

    return new SigninResult(SigninStatus.PASSWORD_MISMATCH, null, null);
  }


  public record CreateResult(Boolean status, String log) {}

  // アカウント作成の成功判定メソッド
  public CreateResult createUser(UserAccountCreateRequest form){


    short roleId;
    if(Boolean.TRUE.equals(form.getAdmin())){
      roleId = 2;
    } else {
      roleId = 1;
    }


    // セッションのログインユーザroleId（2,3）を確認
    if (!Objects.equals(form.getPassword(), form.getCheck())) {

      // TODO: 例外エラーメッセージ
      logger.warn("Password confirmation mismatch");
      return new CreateResult(false, "パスワードが一致していません");

    } else {

      // TODO: 例外エラー処理・メッセージ 例：userIdが存在していた場合
      UserAccount user = new UserAccount();
      user.setUserId(form.getUserId());
      user.setPassword(passwordEncoder.encode(form.getPassword()));
      user.setName(form.getName());
      user.setRoleId(roleId);
      user.setIsActive(true);

      userAccountRepository.save(user);

      // TODO: 例外エラー処理・メッセージ 例：それぞれの被ってはいけない項目の値が被っていた場合。
      UserProfile profile = new UserProfile();
      profile.setUserId(form.getUserId());
      profile.setEmail(form.getEmail());
      profile.setWorkplaceId(form.getWorkplaceId());
      profile.setDepartmentId(form.getDepartmentId());

      String phonenumber = form.getPhoneNumber();

      if (!phonenumber.matches("[0-9]+")){
      return new CreateResult(false, "電話番号は数字のみで入力してください。");
    }
      profile.setPhoneNumber(phonenumber);

      userProfileRepository.save(profile);

      // TODO: 削除予定 ログ
      logger.info("Successfully created a user.");
      return new CreateResult(false, "アカウント作成に成功しました。");
    }

  }

  public List<departments> getDepartments() {
    return departmentsRepository.findAll();
  }

  public List<workplaces> getWorkplaces() {
    return workplacesRepository.findAll();
  }

  // アカウント一覧取得メソッド
  public List<UserListRequest> getUser(Short sessionRoleId) {
    List<UserAccount> accounts;
    if (sessionRoleId != null && sessionRoleId.shortValue() == 3) {
      accounts = userAccountRepository.findByIsActiveTrue(Sort.by(Sort.Direction.ASC, "userId"));
    } else {
      accounts = userAccountRepository.findByIsActiveTrueAndRoleIdNot(
          Sort.by(Sort.Direction.ASC, "userId"),
          (short) 3
      );
    }
    List<UserProfile> profiles = userProfileRepository.findAll();
    List<departments> departmentList = departmentsRepository.findAll();

    Map<String, Integer> userDepartmentMap = new HashMap<>();
    for (UserProfile profile : profiles) {
      userDepartmentMap.put(profile.getUserId(), profile.getDepartmentId());
    }

    Map<Integer, String> departmentNameMap = new HashMap<>();
    for (departments department : departmentList) {
      departmentNameMap.put(department.getDepartmentId(), department.getDepartmentName());
    }

    List<UserListRequest> result = new ArrayList<>();
    for (UserAccount account : accounts) {
      Integer departmentId = userDepartmentMap.get(account.getUserId());
      String departmentName = "";
      if (departmentId != null) {
        departmentName = departmentNameMap.getOrDefault(departmentId, "");
      }

      UserListRequest row = new UserListRequest();
      row.setUserId(account.getUserId());
      row.setName(account.getName());
      row.setDepartmentName(departmentName);
      row.setRoleId(account.getRoleId());
      result.add(row);
    }

    return result;
  }

  public Optional<UserAccount> findUserAccount(String userId) {
    if (userId == null || userId.isBlank()) {
      return Optional.empty();
    }
    return userAccountRepository.findByUserId(userId);
  }

  public Optional<UserProfile> findUserProfile(String userId) {
    if (userId == null || userId.isBlank()) {
      return Optional.empty();
    }
    return userProfileRepository.findByUserId(userId);
  }

  // アカウント削除の成功判定メソッド
  public Boolean deleteUser(String userId) {
    if (userId == null || userId.isBlank()) {
      return false;
    }

    Optional<UserAccount> userOpt = userAccountRepository.findByUserId(userId);
    if (userOpt.isEmpty()) {
      return false;
    }

    // superadminは絶対に削除できない
    UserAccount user = userOpt.get();
    if (user.getRoleId() == 3) {
      return false;
    }
    if (!Boolean.FALSE.equals(user.getIsActive())) {
      user.setIsActive(false);
      userAccountRepository.save(user);
    }
    return true;
  }

  // アカウント編集の成功判定メソッド

  public record EditResult(Boolean status, String log) {}

  public EditResult editUser(UserAccountUpdateRequest form) {
    if (form == null || form.getUserId() == null) {
      return new EditResult(false, "入力されていません。");
    }

    Optional<UserAccount> userAccount = userAccountRepository.findByUserId(form.getUserId());
    Optional<UserProfile> userProfile = userProfileRepository.findByUserId(form.getUserId());

    UserAccount userA = userAccount.get();
    userA.setName(form.getName());

    short roleId;
    if(Boolean.TRUE.equals(form.getAdmin())){
      roleId = 2;
    } else {
      roleId = 1;
    }

    userA.setRoleId(roleId);

    boolean hasPassword = form.getPassword() != null;
    boolean hasCheck = form.getCheck() != null;
    boolean passwordMatch = Objects.equals(form.getPassword(), form.getCheck());

    if (!hasPassword && !hasCheck) {
      // 現在のパスワードを保持する
    } else if (hasPassword && hasCheck && passwordMatch) {
      userA.setPassword(passwordEncoder.encode(form.getPassword()));
    } else {
      // TODO: 例外エラーメッセージ
      logger.warn("Password confirmation mismatch");
      return new EditResult(false, "パスワードが一致していません。");
    }

    UserProfile userP = userProfile.get();
    userP.setWorkplaceId(form.getWorkplaceId());
    userP.setDepartmentId(form.getDepartmentId());

    String phonenumber = form.getPhoneNumber();
    if (!phonenumber.matches("[0-9]+")){
      return new EditResult(false, "電話番号は数字のみで入力してください。");
    }
    userP.setPhoneNumber(form.getPhoneNumber());
    userP.setEmail(form.getEmail());

    userAccountRepository.save(userA);
    userProfileRepository.save(userP);

    logger.info("Succesfully updated user.");

    return new EditResult(true, "ユーザーの情報を更新しました。");
  }

}
