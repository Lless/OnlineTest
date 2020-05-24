package com.github.lless.OnlineTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullStatsDto {
    private Long registeredUsersCount;
    private Long testedUsersCount;
    private Long testFinishedUsersCount;
    private Long AllCorrectUsersCount;
}
