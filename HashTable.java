import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

/**
Generically typed HashTable class. 
*/
public class HashTable<K, V>{
	//array size_m m
	private int size_m;
	private int keys_n;
	//index of the prime we are using
	private int prime_ind;
	private ArrayList<HashElem<K, V>> hash_table;
	private static int[] prime_array = {11, 19, 41, 79, 163, 317, 641, 1279, 2557, 5119, 10243, 20479, 40961, 81919, 163841, 327673};
	public int makeHash(K obj, int prime_loc){
		return Math.abs(obj.hashCode()%prime_array[prime_loc]);
	}

	public HashTable(){
		hash_table = new ArrayList<HashElem<K, V>>(prime_array[0]);
		int i;
		for(i = 0; i < prime_array[0]; i++){
			hash_table.add(i, null);
		}
		size_m = prime_array[0];
		prime_ind = 0;
	}
	//maps the key to a value in the table
	public void put(K key, V value){
		int tab_key = Math.abs(key.hashCode()%size_m);
		HashElem<K, V> put_elem;
		HashElem<K, V> temp_elem;
		HashElem<K, V> help_elem;
		//if there is already an element at that index
		if(hash_table.get(tab_key) != null){
			temp_elem = hash_table.get(tab_key);
			//variable that tells us what to do in various cases
			int cond = 0;
			//go down the chain until we find the element
			do{
				//if we are putting in an element we already have in the table
				help_elem = temp_elem;
				if(key.equals(temp_elem.getKey())){
					//we have found our element, so break the loop
					cond = 1;
					break;
				}
				if(temp_elem.getNext() != null){
					temp_elem = temp_elem.getNext();
					help_elem = help_elem.getNext();
				}
				else{
					break;
				}
			}while(true);
			//the element was already in the table
			//and we're just changing the value
			if(cond == 1){
				temp_elem.setValue(value);
			}
			//the element wasn't in the table, so 
			//put it at the end of the chain
			else if(temp_elem.getNext() == null){
				put_elem = new HashElem(key, value, temp_elem, null);
				temp_elem.setNext(put_elem);
				keys_n++;
			}
		}
		//if there is no element at that index
		else{
			put_elem = new HashElem(key, value, null, null);
			keys_n++;
			hash_table.set(tab_key, put_elem);
		}
		if(this.loadFactor() > 0.75){
			this.reHash();
		}
	}

	//returns the value associated with a certain key
	public V get(K key){
		int tab_index = Math.abs(key.hashCode()%size_m);
		HashElem<K, V> temp_elem = hash_table.get(tab_index);
		while(temp_elem != null){
			//if this is the key
			if(temp_elem.getKey().equals(key)){
				//return the value
				return temp_elem.getValue();
			}
			//keep looking down the chain
			temp_elem = temp_elem.getNext();
		}
		return null;
	}

	//checks whether a key is valid in the table
	public boolean contains(K key){
		int tab_index = Math.abs(key.hashCode()%size_m);
		HashElem<K, V> temp_elem = hash_table.get(tab_index);
		while(temp_elem != null){
			//if this is the key
			if(temp_elem.getKey().equals(key)){
				//return true
				return true;
			}
			//keep looking down the chain
			temp_elem = temp_elem.getNext();
		}
		//if we didn't find it
		return false;
	}
	//deletes an element at a certain key
	public void delete(K key){
		int tab_index = Math.abs(key.hashCode()%size_m);
		HashElem next_e;
		HashElem prev_e;
		HashElem<K, V> temp_elem = hash_table.get(tab_index);
		if(temp_elem == null){
			//if we don't find it do nothing
		}
		//if the first element of chain contains the key
		else if(temp_elem.getKey().equals(key)){
			//get the next element in chain.
			next_e = temp_elem.getNext();
			//if not null, reset the 'prev' value of this element to null
			if(next_e != null){
				next_e.setPrev(null);
			}
			//set this value to whatever was next (could have been null)
			hash_table.set(tab_index, next_e);
			System.out.println("\""+key+"\" has been deleted.");
		}
		//otherwise move down the chain
		else{
			while(temp_elem != null){
				//if this is the key
				if(temp_elem.getKey().equals(key)){
					//unlink from the chain
					next_e = temp_elem.getNext();
					prev_e = temp_elem.getPrev();
					prev_e.setNext(next_e);
					if(next_e != null){
						next_e.setPrev(prev_e);
					}
					System.out.println("\""+key+"\" has been deleted.");
					break;
					//i guess the java garbage collector can take care of it now...
				}
				//keep looking down the chain
				temp_elem = temp_elem.getNext();
			}
		}
	}
	public int getSize(){
		return size_m;
	}
	public int getDistinct(){
		return keys_n;
	}
	//calculates the load factor α = n/m
	public double loadFactor(){
		return (double)keys_n/(double)size_m;
	}

	//rehashes the table with the next prime
	public void reHash(){
		keys_n = 0;
		int i;
		int new_hash;
		HashElem<K, V> re_elem;
		K re_key;
		V re_value;
		//store the old table
		ArrayList<HashElem<K, V>> old_table = hash_table;
		//increment to and get the next prime
		prime_ind++;
		int old_size = size_m;
		size_m = prime_array[prime_ind];
		//reassign our new hash table arraylist
		hash_table = new ArrayList<HashElem<K, V>>(size_m);
		for(i = 0; i < size_m; i++){
			hash_table.add(i, null);
		}
		//go through everything in the old table and rehash
		for(i = 0; i < old_size; i++){
			if(old_table.get(i) != null){
				re_elem = old_table.get(i);
				while(re_elem != null){
					//get the element's key and value
					re_key = re_elem.getKey();
					re_value = re_elem.getValue();
					//put the element in the new hash table
					this.put(re_key, re_value);
					//check if the chain has next elements
					//continue rehashing if it does
					re_elem = re_elem.getNext();
				}
			}
		}
	}
	//Prints the table, for testing purposes. 
	public void printTable(){
		int i;
		System.out.println("----Hash Table----");
		for(i = 0; i < hash_table.size(); i++){
			String str = "--";
			if(hash_table.get(i) != null){
				K tk = hash_table.get(i).getKey();
				V tv = hash_table.get(i).getValue();
				String ks = tk.toString();
				String vs = tv.toString();
				System.out.println(i + " Key: "+ks+" Value: "+vs);
				HashElem tmp = hash_table.get(i).getNext();
				while(tmp != null){
					System.out.println(i + str+" Key: "+tmp.getKey()+" Value: "+tmp.getValue());
					tmp=tmp.getNext();
					str = str + "--";
				}
			}
			else{
				System.out.println(i + " null");
			}
		}
		System.out.println("------------------");
	}
}
