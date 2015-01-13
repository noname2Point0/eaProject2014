package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;

import java.util.List;

import org.springframework.context.ApplicationContext;

public abstract class ReserveSchedule {


	public static boolean isAnAvailableReserve(ApplicationContext context,String dataService, String time){

		String [] splitMyReserveTime = time.split(":");
		int hour = Integer.valueOf(splitMyReserveTime[0]);
		if(hour<8 || hour>19){
			return false;
		}

		String query = "from Reserve r where r.dateService='"+dataService+"'";
		List<Reserve> reserves = QueryFactory.create(context, query);
		System.out.println("query effettuata con successo");

		if(reserves.isEmpty()){
			return true;
		}
		boolean canIreserve = false;

		String [] splitAllReserveTime;
		for(int i=0; i<reserves.size(); i++){
			splitAllReserveTime = reserves.get(i).getTime().split(":");

			if(Integer.valueOf(splitAllReserveTime[0]) > Integer.valueOf(splitMyReserveTime[0])){
				return false;
			}

		}
		//devo controllare i minuti
		
		for(int i=0; i<reserves.size(); i++){
			splitAllReserveTime = reserves.get(i).getTime().split(":");

			if(Integer.valueOf(splitAllReserveTime[1]) > Integer.valueOf(splitMyReserveTime[1])){
				return false;
			}
			//se il mio tempo è maggiore...ma non di almeno 15 minuti.
			else if(Integer.valueOf(splitMyReserveTime[1]) > (Integer.valueOf(splitAllReserveTime[1]))){
				if(Integer.valueOf(splitMyReserveTime[1]) >= (Integer.valueOf(splitAllReserveTime[1])+15)){
					canIreserve = true;
				}
				else{
					canIreserve = false;
				}
			}

		}
		return canIreserve;
		
	}

}
