package com.csc.betapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.betapp.dao.BetInfoDao;
import com.csc.betapp.model.BetInfo;
import com.csc.betapp.session.SessionHolder;

@Service("betService")
public class BetService {
	@Autowired
	private BetInfoDao betInfoDao;
	
	public List<Integer> selectionCounter(int matchId){
		List<BetInfo> lstBetInfo = betInfoDao.getAllBetFromMatchId(matchId, SessionHolder.getInstance().getCustomerId());
		int team1Counter = 0;
		int team2Counter = 0;
		for(BetInfo bi : lstBetInfo){
			if (bi.getSelection() == 1) {
				team1Counter ++;
			} else {
				team2Counter ++;
			}
		}
		List<Integer> counters = new ArrayList<Integer>();
		counters.add(team1Counter);
		counters.add(team2Counter);
		return counters;
		
	}
	public BetInfo getBetInfoFromMatchIdAndUserId(int matchId, int userId){
		List<BetInfo> lstBetInfo = betInfoDao.getAllBetFromMatchId(matchId, SessionHolder.getInstance().getCustomerId());
		if(lstBetInfo.size() > 0){
			for(BetInfo bi : lstBetInfo){
				if(bi.getUserId() == userId){
					return bi;
				}
			}
		}
		return null;
	} 
	public void insertBetInfo(BetInfo bi, int customerId){
		betInfoDao.insertBetInfo(bi, customerId);
	}
	
	public void updateSelection(int betInfoId, int result, int customerId){
		betInfoDao.setSelection(betInfoId, result, customerId);
	}
}
