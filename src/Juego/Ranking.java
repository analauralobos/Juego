package Juego;

import java.io.*;
import java.util.*;

public class Ranking{

	private ArrayList<Integer> ranking=new ArrayList<Integer>(1);

	public Ranking(){
		this.cargar("ranking.txt",ranking);
	}

	public void cargar(String nbreArchivo, ArrayList<Integer> ranking){
		try{
			String aux;
			RandomAccessFile archivo = new RandomAccessFile(nbreArchivo, "r");
			while(archivo.getFilePointer() < archivo.length()){
				aux = archivo.readLine(); 
				ranking.add(Integer.parseInt(aux));
			}
			archivo.close();//cierro archivo

		}catch(Exception e){
			System.out.println("*** Error ***");
			System.out.println(e);
		}

        ranking.sort((o1, o2) -> o2.compareTo(o1));
	}

    public void actualizar(int score){
        ranking.add(score);
        ranking.sort((o1, o2) -> o2.compareTo(o1));
        ranking.remove(10);

        try {
            PrintWriter writer = new PrintWriter("ranking.txt", "UTF-8");
            for(Integer r: ranking){
            writer.println(r.toString());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getTop(){
        return ranking.get(0);
    }
	 
}