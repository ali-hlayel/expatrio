package com.expatrio.user.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInput
{
    private String username;
    private String password;
    private String role;
    private String firstname;
    private String lastname;
}
