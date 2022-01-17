package MP2_Problem17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

class newMap<K,V> extends TreeMap<K,V>{
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

class MapManager {
	static Map<String, Double> readData(String fileName){
		int i;
		String line;
		Map<String, Double> map = new newMap<>();
		
		try {
			FileReader rw = new FileReader(fileName);
			BufferedReader br = new BufferedReader(rw);
			
			for(i = 1; (line = br.readLine()) != null; i++) {
				String[] tmp = line.split(" ");
				
				map.put(tmp[0], Double.parseDouble(tmp[1]));
			
			}
		}catch(IOException e) {
			System.out.println("Input file not found.");
		}
		
	
		return map;
	}
	

}

public class Problem17 {

	public static void main(String[] args) {
		Map<String, Double> map = MapManager.readData("input.txt");
		if(map == null) {
		System.out.println("Input file not found.");
		return;
		}
		System.out.println(map);
	}

}
