package com.github.lless.OnlineTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserStatsDto {
    private float percentCorrectAnswers;
    private float percentUsersBetterThanUser;
    private float percentUsersWorseThanUser;
}
