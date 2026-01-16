package com.kyosaka.kintaisan.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kyosaka.kintaisan.entity.UserProfile;
import com.kyosaka.kintaisan.entity.workplaces;
import com.kyosaka.kintaisan.repository.UserProfileRepository;
import com.kyosaka.kintaisan.repository.workplacesRepository;

@Service
public class UserProfileService {

    // インスタンス化
    private final UserProfileRepository userProfileRepository;
    private final workplacesRepository workplacesRepository;

    // コンストラクタ
    public UserProfileService(UserProfileRepository userProfileRepository, workplacesRepository workplacesRepository) {
        this.userProfileRepository = userProfileRepository;
        this.workplacesRepository = workplacesRepository;
    }

    public int getUserWorkplace(String userId){
        Optional<UserProfile>up = userProfileRepository.findByUserId(userId);
        return up.map(UserProfile::getWorkplaceId).orElse(null);
    }

    public List<workplaces> getworkplaces(){
        return workplacesRepository.findAll();
    }

    public String getphone(String userId){
        // もし連絡先が登録されてなければ""を返す
        Optional<UserProfile> up =
        userProfileRepository.findByUserId(userId);
        if (up.isPresent()) {
            UserProfile data = up.get();
            if(data.getPhoneNumber() != null){
                return data.getPhoneNumber();
            }
        }
        return "";
    }
    public int updateUser(String userId, int workplaceId, String phoneNumber){
        Optional<UserProfile> up = userProfileRepository.findByUserId(userId);
        int ret = -1;
        if (up.isPresent()) {
            UserProfile data = up.get();
            data.setWorkplaceId(workplaceId);
            data.setPhoneNumber(phoneNumber);
            userProfileRepository.save(data);
            ret = 1;
        }
        return ret;
    }
}
