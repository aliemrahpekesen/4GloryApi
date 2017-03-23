
package com.glory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Member;
import com.glory.model.Transaction;
import com.glory.model.enums.Status;
import com.glory.service.model.BurnMilesRequest;
import com.glory.service.model.BurnMilesResponse;

@Service
public class BurnMilesService {

	@Autowired
	MemberService memberService;

	@Autowired
	TransactionService transactionService;

	Member member = null;
	Transaction transaction = null;

	public BurnMilesResponse burnMiles(BurnMilesRequest burnMilesRequest) {

		BurnMilesResponse response = new BurnMilesResponse();

		try {
			transaction = transactionService.findById(burnMilesRequest.getAmadeusTransactionId());
			member = memberService.findByMemberId(transaction.getMemberId());
		} catch (Exception e) {
			setFailResponse(burnMilesRequest, response);
			e.printStackTrace();
		}
		// case: successful unblock
		if (unblockMiles(transaction.getMemberId())) {
			setSuccessResponse(burnMilesRequest, response);
			transaction.setStatus(Status.SPENT);
		} else {
			setFailResponse(burnMilesRequest, response);
			transaction.setStatus(Status.ERROR);
		}

		transactionService.update(transaction);
		return response;
	}

	private Boolean unblockMiles(Long memberId) {
		Boolean result = false;
		try {
			Long transactionPoints = transaction.getPoints();

			//float activeMemberPoints = member.getActivePoints();
			float blockedMemberPoints = member.getBlockedPoints();
			float burntMemberPoints = member.getBurntPoints();
			
			member.setBlockedPoints(blockedMemberPoints - transactionPoints); 
			member.setBurntPoints(burntMemberPoints + transactionPoints);
			memberService.update(member.getId(), member);
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private void setSuccessResponse(BurnMilesRequest burnMilesRequest, BurnMilesResponse response) {
		response.setAmadeusTransactionId(burnMilesRequest.getAmadeusTransactionId());
		response.setPartnerTransactionId(burnMilesRequest.getPartnerTransactionId());
		response.setMessage("Burn operation is successfully done");
		response.setMessageCode(0);
		response.setStatus(Status.SPENT.getCode());
	}

	private void setFailResponse(BurnMilesRequest burnMilesRequest, BurnMilesResponse response) {
		response.setAmadeusTransactionId(burnMilesRequest.getAmadeusTransactionId());
		response.setPartnerTransactionId(burnMilesRequest.getPartnerTransactionId());
		response.setMessage("Burn operation has failed");
		response.setMessageCode(1);
		response.setStatus(Status.ERROR.getCode());
	}

}
