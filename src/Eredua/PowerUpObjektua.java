package Eredua;

public class PowerUpObjektua {
    private int x, y;
    private PowerUp efektua;
    private String izena;

    public PowerUpObjektua(int x, int y, String izena, PowerUp efektua) {
        this.efektua = efektua;
        this.x = x;
        this.y = y;
        this.izena=izena;
        MatrizeE.getEma().gelaxkaEguneratu(x, y, new PowerUpEgoera());    }
    
    public void jaitsi() {
        GelaxkaE g= MatrizeE.getEma().getGelaxka(x, y);
        
        //nabearen gelaxka bada, ez dago hustu behar
        if(g!= null && !g.getEntitateMota().equals("espaziontzia")) {
        	MatrizeE.getEma().gelaxkaEguneratu(x,y, new HutsaEgoera());
        }
    	this.y++;
    	//Pantailan barruan dago, margotu
    	if(this.y < 60) {
    		GelaxkaE g2=MatrizeE.getEma().getGelaxka(x, y);
    		//ezn idatzi nabearen gainean
    		if(g2!=null && !g2.getEntitateMota().equals("espaziontzia")) {
    			MatrizeE.getEma().gelaxkaEguneratu(x, y, new PowerUpEgoera());
    		}
    		
    	}
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public String getIzena() {return izena;}
    
    public void aplikatuEfektua(Espaziontzia e) { 
        if (efektua != null) {
            efektua.aplikatu(e);
        }
    }
}