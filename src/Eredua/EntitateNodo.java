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
			return new ArrayList<>(entitateLista)
			        .stream()
			        .allMatch(a -> a.mugituDaiteke(norabidea));		}
		
		public void mugitu(String norabidea) {
		    if (mugituDaiteke(norabidea)) {
		        ordenatuNorabidearenArabera(norabidea)
		            .forEach(a -> a.mugitu(norabidea));
		    }
		}
		
		public void gehituEntitate(Entitatea berria) {
			entitateLista.add(berria);
		}
		
		public List<EntitateInterfazea> ordenatuNorabidearenArabera(String norabidea) {

		    List<EntitateInterfazea> listaOrdenatuta = new ArrayList<>(entitateLista);

		    switch (norabidea) {
		        case "eskuinera": // eskuinera → X handienetik txikienera		        	
		        	listaOrdenatuta=entitateLista.stream().
		        	sorted(java.util.Comparator.comparing(a->((Entitatea) a).
		        			getPosizioa().getX()).reversed()).toList();
		        	break;	        
		        case "ezkerrera": // ezkerrera → X txikienetik handienera
		            
		        	listaOrdenatuta=entitateLista.stream().
		        	sorted(java.util.Comparator.comparing(a->((Entitatea) a).
		        			getPosizioa().getX())).toList();
		        	break;

		        case "behera": // behera → Y handienetik txikienera
		        	listaOrdenatuta=entitateLista.stream().
		        	sorted(java.util.Comparator.comparing(a->((Entitatea) a).
		        			getPosizioa().getY()).reversed()).toList();
		        	break;
		        case "gora": // gora → Y txikienetik handienera
		        	listaOrdenatuta=entitateLista.stream().
		        	sorted(java.util.Comparator.comparing(a->((Entitatea) a).
		        			getPosizioa().getY())).toList();
		        	break;
		    }

		    return listaOrdenatuta;
		}
		

		
}
