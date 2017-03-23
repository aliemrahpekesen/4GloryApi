package com.glory.service;

import org.springframework.stereotype.Service;

import com.glory.model.Card;
import com.glory.model.Member;
import com.glory.service.model.MileCheckRequest;
import com.glory.service.model.MileCheckResponse;

@Service
public class MilesCheckService {

	public MileCheckResponse checkMileAvailability(MileCheckRequest request) {
		MileCheckResponse response = new MileCheckResponse();
		Card requestCardInfo = request.getCardInfo();
		String requestCardNumber = requestCardInfo.getNumber();
		boolean pointsBlocked = false;
		int requestPoints = 0;
		
		//transaction generate et
		
		String flyerCode = extractProgramInformation(requestCardNumber);
		if (flyerCode != null) {
			requestPoints = convertToPoints(request.getCurrency().code(), request.getAmount(), request.getCompanyCode(),
					flyerCode);
			Member requestedMember = matchMemberInformation(requestCardInfo);
			if (requestedMember != null) {
				pointsBlocked = blockMemberPoints(requestedMember, requestPoints, request.getCompanyCode());
			} else {
				response.setStatus("CARD INFORMATION DOES NOT MATCH MEMBER");
				response.setMessageCode(90);
				response.setAmadeusTransactionID(124124123123L);
				return response;
			}

		} else {
			response.setStatus("CARD NUMBER WRONG UNKNOWN PROGRAM");
			response.setMessageCode(91);
			response.setAmadeusTransactionID(21412412412L);
			return response;
		}

		if (pointsBlocked) {
			response.setStatus("CARD NUMBER WRONG UNKNOWN PROGRAM");
			response.setMessageCode(92);
			response.setAmadeusTransactionID(12412412L);
			return response;
		} else {
			response.setStatus("POINT BLOCKED");
			response.setMessageCode(0);
			response.setAmadeusTransactionID(124123123234746L);
			return response;
		}

	}

	private int convertToPoints(String currency, float amount, String companyCode, String flyerCode) {
		// TODO conversion functionality with currency and company information
		// for matching rule
		//Validation for if rule exists
		return 0;
	}

	private String extractProgramInformation(String cardNumber) {
		// TODO lookup from predefined air company and number if not found
		// return null
		return "TK";
	}

	private Member matchMemberInformation(Card requestCardInfo) {
		// TODO DAO and impl with member select info
		return new Member();
	}

	private boolean blockMemberPoints(Member requestedMember, int requestPoints, String string) {
		// TODO DAO and impl with block alterations
		return true;
	}

}
