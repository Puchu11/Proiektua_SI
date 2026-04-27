package Eredua;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntitateNodo implements EntitateInterfazea {

		private ArrayList<EntitateInterfazea> entitateLista = new ArrayList<EntitateInterfazea>();
		
		public EntitateNodo() {}
		
		public ArrayList<EntitateInterfazea> getLista(){
			return entitateLista;
		}
		
		public Iterator<EntitateInterfazea> getIterator(){
			return entitateLista.iterator();
		}
		
		public boolean mugituDaiteke(String norabidea) {
			for(EntitateInterfazea a: entitateLista) {
				if(!a.mugituDaiteke(norabidea)) {
					return false;
				}
			}
			return true;
		}
		
		public void mugitu(String norabidea) {
		    if (mugituDaiteke(norabidea)) {	        
		        // Mugitu dena
		        List<EntitateInterfazea> lista = ordenatuNorabidearenArabera(norabidea);
		        
		        for (EntitateInterfazea a : lista) {
		            a.mugitu(norabidea);
		        }
		    }
		}
		
		public void gehituEntitate(Entitatea berria) {
			entitateLista.add(berria);
		}
		
		public List<EntitateInterfazea> ordenatuNorabidearenArabera(String norabidea) {

		    List<EntitateInterfazea> listaOrdenatuta = new ArrayList<>(entitateLista);

		    switch (norabidea) {

		        case "eskuinera": // eskuinera → X handienetik txikienera
		            listaOrdenatuta.sort((a, b) ->
		                ((Entitatea)b).getPosizioa().getX() - ((Entitatea)a).getPosizioa().getX()
		            );
		            break;
		        
		        case "ezkerrera": // ezkerrera → X txikienetik handienera
		            listaOrdenatuta.sort((a, b) ->
		                ((Entitatea)a).getPosizioa().getX() - ((Entitatea)b).getPosizioa().getX()
		            );
		            break;

		        case "behera": // behera → Y handienetik txikienera
		            listaOrdenatuta.sort((a, b) ->
		                ((Entitatea)b).getPosizioa().getY() - ((Entitatea)a).getPosizioa().getY()
		            );
		            break;
		            

		        case "gora": // gora → Y txikienetik handienera
		            listaOrdenatuta.sort((a, b) ->
		                ((Entitatea)a).getPosizioa().getY() - ((Entitatea)b).getPosizioa().getY()
		            );
		            break;
		    }

		    return listaOrdenatuta;
		}
		

		
}
