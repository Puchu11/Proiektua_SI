package Eredua;

public class EtsaiFactory {

	private static EtsaiFactory nireFactory;

	private EtsaiFactory() {}

	public static EtsaiFactory getEtsaiFactory() {
		if (nireFactory == null) {
			nireFactory = new EtsaiFactory();
		}
		return nireFactory;
	}

	public EntitateNodo sortuEtsaia(String mota, int x, int y, int id) {
		if (mota.equalsIgnoreCase("txikia")) {
			return etsaiaTxikia(x, y, id);
		}
		else if (mota.equalsIgnoreCase("ertaina")) {
			return etsaiaErtaina(x, y, id);
		}
		else if (mota.equalsIgnoreCase("handia")) {
			return etsaiaHandia(x, y, id);
		}
		else if (mota.equalsIgnoreCase("tiratzailea")) {
			return etsaiaTiratzailea(x, y, id);
		}

		return etsaiaTxikia(x, y, id);
	}

	private EntitateNodo etsaiaTxikia(int x, int y, int id) {
		EntitateNodo nodo = new EntitateNodo();

		int[][] koordenatuak = {
			{0, 0},
			{-1, 1}, {0, 1}, {1, 1},
			{-1, 2}, {1, 2}
		};

		for (int[] pos : koordenatuak) {
			nodo.gehituEntitate(new Etsaia(x + pos[0], y + pos[1], id));
		}

		return nodo;
	}

	private EntitateNodo etsaiaErtaina(int x, int y, int id) {
		EntitateNodo nodo = new EntitateNodo();

		int[][] koordenatuak = {
			{0, 0},
			{-1, 1}, {0, 1}, {1, 1},
			{-2, 2}, {-1, 2}, {0, 2}, {1, 2}, {2, 2},
			{-2, 3}, {2, 3},
			{-1, 4}, {1, 4}
		};

		for (int[] pos : koordenatuak) {
			nodo.gehituEntitate(new Etsaia(x + pos[0], y + pos[1], id));
		}

		return nodo;
	}

	private EntitateNodo etsaiaHandia(int x, int y, int id) {
		EntitateNodo nodo = new EntitateNodo();

		int[][] koordenatuak = {
			{-1, 0}, {0, 0}, {1, 0},
			{-2, 1}, {-1, 1}, {0, 1}, {1, 1}, {2, 1},
			{-3, 2}, {-1, 2}, {0, 2}, {1, 2}, {3, 2},
			{-3, 3}, {-2, 3}, {0, 3}, {2, 3}, {3, 3},
			{-2, 4}, {2, 4}
		};

		for (int[] pos : koordenatuak) {
			nodo.gehituEntitate(new Etsaia(x + pos[0], y + pos[1], id));
		}

		return nodo;
	}

	private EntitateNodo etsaiaTiratzailea(int x, int y, int id) {
		EntitateNodo nodo = new EntitateNodo();

		int[][] koordenatuak = {
			{0, 0},
			{-1, 1}, {0, 1}, {1, 1},
			{-2, 2}, {0, 2}, {2, 2},
			{-1, 3}, {0, 3}, {1, 3},
			{0, 4}
		};

		for (int[] pos : koordenatuak) {
			nodo.gehituEntitate(new Etsaia(x + pos[0], y + pos[1], id));
		}

		return nodo;
	}
}