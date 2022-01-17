package MP2_Problem18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

class newMap<K,V> extends LinkedHashMap<K,V>{
	public String toString() {
		Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();

        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append(' ');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.toString();
            sb.append('\n');
        }
	}
}

class MapManager{
	static Map<String, Double> readData(String fileName){
		int i;
		String line;
		double dTmp;
		String sTmp;
		int index;
		Map<String, Double> map = new newMap<>();
		ArrayList<String> sList = new ArrayList<>();
		ArrayList<Double> dList = new ArrayList<>();
				
		try {
			FileReader rw = new FileReader(fileName);
			BufferedReader br = new BufferedReader(rw);
			
			for(i = 1; (line = br.readLine()) != null; i++) {
				String[] tmp = line.split(" ");
				
				sList.add(tmp[0]);
				dList.add(Double.parseDouble(tmp[1]));					
			}	
			
	
			for(i=0; i<sList.size(); i++) {
				sTmp = sList.get(i);
				dTmp = dList.get(i);
				index = i;
				
				for(int j = i+1; j < sList.size(); j++) {
					if(dTmp > dList.get(j)) {
						dTmp = dList.get(j);
						index = j;
					}
					else if(dTmp == dList.get(j)) {
						if(sTmp.compareTo(sList.get(j)) > 0) {
							dTmp = dList.get(j);
							index = j;
						}
					}
				}
					
				map.put(sList.get(index), dList.get(index));
				Collections.swap(sList, i, index);
				Collections.swap(dList, i, index);
			}
			
			
		}catch(IOException e) {
			System.out.println("Input file not found.");
		}
		
		
		return map;
	}
}


public class Problem18 {

	public static void main(String[] args) {
		Map<String, Double> map = MapManager.readData("input.txt");
		if(map == null) {
		System.out.println("Input file not found.");
		return;
		}
		System.out.println(map);
	}

}
