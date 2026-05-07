package Eredua;

import java.util.List;
import java.util.ArrayList;

public class TiroNodoa extends Thread implements TiroInterfazea {
	private List<TiroInterfazea> tiroLista = new ArrayList<>();
	private volatile boolean bizirik;
	
	public TiroNodoa () {
		this.bizirik=true;
	}
	public void gehituTiroa(TiroInterfazea t) {
        tiroLista.add(t);
    }
	
	@Override
	public void run() {
		while(bizirik) {
			try {
				Thread.sleep(50);
			}catch (InterruptedException e) {
				bizirik=false;
				break;
			}
			
			if(kolizioa()) {
				hil();
			}else {
				mugitu();
			}				
		}
	}
	
	public void mugitu(){
		String norabidea = ((Tiro) tiroLista.get(0)).getNorabidea();
		tiroLista=ordenatuNorabidearenArabera(norabidea);
	    tiroLista.forEach(TiroInterfazea::mugitu);
	}
	private boolean kolizioa() {
	    return new ArrayList<>(tiroLista)
	            .stream()
	            .map(t -> (Tiro) t)
	            .anyMatch(Tiro::kolizioa);
	}
	public void hil() {
	    tiroLista.forEach(TiroInterfazea::hil);
	    bizirik = false;
	}
	public List<TiroInterfazea> ordenatuNorabidearenArabera(String norabidea) {

	    List<TiroInterfazea> listaOrdenatuta = new ArrayList<>(tiroLista);

	    switch (norabidea) {
	        case "behera": // behera → Y handienetik txikienera
	        	listaOrdenatuta=tiroLista.stream().
	        	sorted(java.util.Comparator.comparing(a->((Tiro) a).
	        			getPosizioa().getY()).reversed()).toList();
	        	break;
	        case "gora": // gora → Y txikienetik handienera
	        	listaOrdenatuta=tiroLista.stream().
	        	sorted(java.util.Comparator.comparing(a->((Tiro) a).
	        			getPosizioa().getY())).toList();
	        	break;
	    }
	    return listaOrdenatuta;
	}
}
