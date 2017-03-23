package com.glory.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glory.model.Card;
import com.glory.model.Member;
import com.glory.model.Transaction;
import com.glory.model.enums.Status;
import com.glory.service.model.ConversionRequest;
import com.glory.service.model.MileCheckRequest;
import com.glory.service.model.MileCheckResponse;

@Service
public class MilesCheckService {

	@Autowired
	CardService cardService;

	@Autowired
	MemberService memberService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	PointConversionService pointConversionService;

	public MileCheckResponse checkMileAvailability(MileCheckRequest request) {
		MileCheckResponse response = new MileCheckResponse();
		Card requestCardInfo = request.getCardInfo();
		String requestCardNumber = requestCardInfo.getNumber();
		boolean pointsBlocked = false;
		float requestPoints = 0;
		Transaction amadeusTransaction = new Transaction();

		String flyerCode = extractProgramInformation(requestCardNumber);
		if (flyerCode != null) {
			requestPoints = convertToPoints(request.getAmount(), request.getCompanyCode(), flyerCode);
			Member requestedMember = matchMemberInformation(requestCardInfo);
			response.setPartnerTransactionCode(request.getPartnerTransactionCode());

			if (requestedMember != null) {
				pointsBlocked = blockMemberPoints(requestedMember, requestPoints, request.getCompanyCode());
				amadeusTransaction.setPoints((long) requestPoints);
				amadeusTransaction.setStartTime(LocalDateTime.now());
				amadeusTransaction.setStatus(Status.INITIALIZED);
				transactionService.create(amadeusTransaction);
			} else {
				response.setMessage("CARD INFORMATION DOES NOT MATCH MEMBER");
				response.setMessageCode(90);
				response.setStatus(Status.ERROR.getCode());

				return response;
			}

		} else {
			response.setMessage("CARD NUMBER WRONG UNKNOWN PROGRAM");
			response.setMessageCode(91);
			response.setStatus(Status.ERROR.getCode());
			return response;
		}

		if (pointsBlocked) {
			response.setMessage("POINT BLOCKED");
			response.setMessageCode(0);
			response.setAmadeusTransactionID(amadeusTransaction.getId());
			amadeusTransaction.setStatus(Status.BLOCKED);
			transactionService.update(amadeusTransaction);
			return response;
		} else {
			response.setMessage("CARD NUMBER WRONG UNKNOWN PROGRAM");
			response.setMessageCode(92);
			response.setStatus(Status.ERROR.getCode());
			return response;
		}

	}

	private float convertToPoints(float amount, String partnerCompanyCode, String ffpCode) {

		ConversionRequest cr = new ConversionRequest();
		cr.setFfpProgramCode(ffpCode);
		cr.setPartnerCompanyCode(partnerCompanyCode);
		cr.setMonetaryAmount(amount);
		return pointConversionService.convert(cr).getMilesAmount();

	}

	private String extractProgramInformation(String cardNumber) {
		Card card = cardService.findByNumber(cardNumber);
		return card != null ? card.getFfpCode() : null;

	}

	private Member matchMemberInformation(Card requestCardInfo) {
		Member result = null;
		try {
			Card card = cardService.findByNumber(requestCardInfo.getNumber());
			result = memberService.findByCardI(card.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	private boolean blockMemberPoints(Member requestedMember, float requestPoints, String string) {

		boolean result = false;

		if (requestedMember.getActivePoints() >= requestPoints) {
			requestedMember.setBlockedPoints(requestedMember.getBlockedPoints() + requestPoints);
			requestedMember.setActivePoints(requestedMember.getActivePoints() - requestPoints);
			try {
				memberService.update(requestedMember.getId(), requestedMember);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
