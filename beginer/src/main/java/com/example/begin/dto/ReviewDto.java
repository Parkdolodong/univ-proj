package com.example.begin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDto {
    private String result;

    /* review를 작성한 사람 */
    private Long userIdx;
    private String userId;

    /* review를 단 책정보 */
    private Long bookIdx;

    /* review infos */
    private Long idx;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
