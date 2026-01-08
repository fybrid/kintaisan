package com.kyosaka.kintaisan.service;

import java.util.Objects;
import java.util.Optional;
import com.kyosaka.kintaisan.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kyosaka.kintaisan.dto.UserAccountCreateRequest;
import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.entity.UserProfile;
import com.kyosaka.kintaisan.repository.UserAccountRepository;

@Service
public class UserAccountService {

  private final UserAccountRepository userAccountRepository;
  private final UserProfileRepository userProfileRepository;
  private final PasswordEncoder passwordEncoder;
  private static final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

  public UserAccountService(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository, UserProfileRepository userProfileRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userAccountRepository = userAccountRepository;
    this.userProfileRepository = userProfileRepository;
  }

  public enum SigninStatus {
    SUCCESS,
    BAD_REQUEST,
    USER_NOT_FOUND,
    PASSWORD_MISMATCH
  }

  public record SigninResult(SigninStatus status, String userId, Short roleId) {}

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

  public boolean createUser(UserAccountCreateRequest form){

    short roleId;
    if(Boolean.TRUE.equals(form.getAdmin())){
      roleId = 2;
    } else {
      roleId = 1;
    }

    if (!Objects.equals(form.getPassword(), form.getCheck())) {
      logger.warn("Password confirmation mismatch");
      return false; // TODO: 例外エラーメッセージ
    } else {
      // TODO: 例外エラー処理 userIdが存在していた場合
      UserAccount user = new UserAccount();
      user.setUserId(form.getUserId());
      user.setPassword(passwordEncoder.encode(form.getPassword()));
      user.setName(form.getName());
      user.setRoleId(roleId);
      user.setIsActive(true);
  
      userAccountRepository.save(user);
  
      UserProfile profile = new UserProfile();
      profile.setUserId(form.getUserId());
      profile.setEmail(form.getEmail());
      profile.setWorkplaceId(form.getWorkplaceId());
      profile.setDepartmentId(form.getDepartmentId());
      profile.setPhoneNumber(form.getPhoneNumber());
  
      userProfileRepository.save(profile);
  
      // TODO: 削除予定 ログ
      logger.info("Successfully created a user.");
      return true;
    }

  }

}
