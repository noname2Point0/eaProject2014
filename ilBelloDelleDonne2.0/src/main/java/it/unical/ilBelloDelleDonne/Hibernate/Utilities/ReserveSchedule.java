package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;

import java.util.List;

import org.springframework.context.ApplicationContext;

public abstract class ReserveSchedule {


	public static boolean isAnAvailableReserve(ApplicationContext context,String dataService, String time){

		String [] splitMyReserveTime = time.split(":");

		String query = "from Reserve r where r.dateService='"+dataService+"'";
		List<Reserve> reserves = QueryFactory.create(context, query);

		if(reserves.isEmpty()){
			return true;
		}

		String [] splitAllReserveTime;

		for(int i=0; i<reserves.size(); i++){
			splitAllReserveTime = reserves.get(i).getTime().split(":");
			if(Integer.valueOf(splitAllReserveTime[0]) == Integer.valueOf(splitMyReserveTime[0])){

				int diff = (Integer.valueOf(splitAllReserveTime[1]) - Integer.valueOf(splitMyReserveTime[1]));
				if(Math.abs(diff) < 15){
					return false;
				}

			}
			else if(Integer.valueOf(splitAllReserveTime[0]) < Integer.valueOf(splitMyReserveTime[0])){
				int diff = (Integer.valueOf(splitAllReserveTime[0]) - Integer.valueOf(splitMyReserveTime[0]));
				if(Math.abs(diff) < 2){
					if(Integer.valueOf(splitAllReserveTime[1]) > 45){
						int diffToZero = 60-Integer.valueOf(splitAllReserveTime[1]);
						int diffBetweenTwoTimeReserve = diffToZero + Integer.valueOf(splitMyReserveTime[1]);						
						if(diffBetweenTwoTimeReserve < 15){
							return false;
						}
					}
				}
			}
			else if(Integer.valueOf(splitAllReserveTime[0]) > Integer.valueOf(splitMyReserveTime[0])){
				int diff = (Integer.valueOf(splitAllReserveTime[0]) - Integer.valueOf(splitMyReserveTime[0]));
				if(Math.abs(diff) < 2){
					if(Integer.valueOf(splitMyReserveTime[1]) > 45){
						int diffToZero = 60-Integer.valueOf(splitMyReserveTime[1]);
						int diffBetweenTwoTimeReserve = diffToZero + Integer.valueOf(splitAllReserveTime[1]);
						if(diffBetweenTwoTimeReserve < 15){
							return false;
						}
					}
				}
			}

		}
		return true;

	}

}
