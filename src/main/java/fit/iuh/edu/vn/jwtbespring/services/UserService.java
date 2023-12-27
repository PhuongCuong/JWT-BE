package fit.iuh.edu.vn.jwtbespring.services;

import fit.iuh.edu.vn.jwtbespring.dto.AccountUserDTO;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.response.UserResponse;
import fit.iuh.edu.vn.jwtbespring.responsitory.UserResponsitory;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;

    private final UserResponsitory userResponsitory;

    public UserResponse getdatauser(HttpServletRequest req, String nCookie) {
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (c.getName().equals(nCookie)) {
                    String email = jwtService.extractUsername(c.getValue());
                    System.out.println(email);
                    Optional<User> user = userResponsitory.findUserByEmail(email);
                    if (user.isPresent()) {
                        return UserResponse
                                .builder()
                                .EC(0)
                                .EM("get data success")
                                .DT(
                                        new AccountUserDTO(user.get().getFirstName(),
                                                user.get().getLastName(),
                                                user.get().getAddress(),
                                                user.get().getPhone(),
                                                user.get().getEmail())
                                )
                                .build();
                    } else {
                        return UserResponse
                                .builder()
                                .EC(1)
                                .EM("get data error")
                                .DT(
                                        new AccountUserDTO()
                                )
                                .build();
                    }
                }
            }
        }
        return UserResponse
                .builder()
                .EC(1)
                .EM("get data error")
                .DT(
                        new AccountUserDTO()
                )
                .build();
    }

//    public UserResponse getAllUsers() {
//        List<User> users = userResponsitory.findAll();
//        if (users != null && !users.isEmpty()) {
//            return UserResponse.builder()
//                    .EC(0)
//                    .EM("get data success")
//                    .DT("")
//                    .data(users)
//                    .build();
//        } else {
//            return UserResponse.builder()
//                    .EC(1)
//                    .EM("no data found or data error")
//                    .DT("")
//                    .data(Collections.emptyList())
//                    .build();
//        }
//
//
//    }

    public List<User> getAllUsers(){
        return userResponsitory.findAll();
    }

}
