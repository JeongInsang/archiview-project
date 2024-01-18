package com.ssafy.archiview.service.reply;

import com.ssafy.archiview.dto.reply.ReplyDto;

public interface ReplyService {
    // 답변 상세 조회
    ReplyDto.DetailResponseDto replyDetail(ReplyDto.DetailRequestDto requestDto);
    // 답변 삭제
    void replyDelete(int id);
}
