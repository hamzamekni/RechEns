package com.example.resens.dto;
import com.example.resens.enumeration.Role;
import com.example.resens.model.User;
import lombok.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfileResponse {
    private Long id ;
    private String firstName ;
    private String lastName ;
    private Date birthday;
    private String email;
    private Role role;
    private Integer phoneNumber;
    private byte[] profileImage;
    public static ProfileResponse fromEntity(User user)
    {
        if(user == null)
        {
            return null ;
        }

        return ProfileResponse.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .profileImage(user.getProfileImage())
                .build() ;
    }


}
