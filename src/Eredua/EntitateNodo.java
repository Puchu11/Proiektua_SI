package Eredua;

import java.util.ArrayList;
import java.util.Iterator;

public class EntitateNodo implements EntitateInterfazea {

		private ArrayList<Entitatea> entitateLista = new ArrayList<Entitatea>();
		
		public EntitateNodo() {}
		
		public ArrayList<Entitatea> getLista(){
			return entitateLista;
		}
		
		public Iterator<Entitatea> getIterator(){
			return entitateLista.iterator();
		}
		
		public boolean mugituDaiteke(String norabidea) {
			for(Entitatea a: entitateLista) {
				if(!a.mugituDaiteke(norabidea)) {
					return false;
				}
			}
			return true;
		}
		
		public void mugitu(String norabidea) {
		    if (mugituDaiteke(norabidea)) {

		        // Ezabatu pozizio zahrrak
		        for (Entitatea a : entitateLista) {
		            MatrizeE.getEma().getMatrizea()[a.getPosizioa().getY()][a.getPosizioa().getX()]
		                .gelaxkaEguneratu(new HutsaEgoera());
		        }

		        // Mugitu dena
		        for (Entitatea a : entitateLista) {
		            a.mugitu(norabidea);
		        }

		        //marraztu dena
		        for (Entitatea a : entitateLista) {
		            if (a instanceof Etsaia) {
		                MatrizeE.getEma().getMatrizea()[a.getPosizioa().getY()][a.getPosizioa().getX()]
		                    .gelaxkaEguneratu(new EtsaiaEgoera());
		            } else {
		                MatrizeE.getEma().getMatrizea()[a.getPosizioa().getY()][a.getPosizioa().getX()]
		                    .gelaxkaEguneratu(new EspaziontziaEgoera());
		            }
		        }
		    }
		}
		
		public void gehituEntitate(Entitatea berria) {
			entitateLista.add(berria);
		}
		
		

		
}
