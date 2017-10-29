import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

public class HashTable<K, V>{
	//array size_m m
	private int size_m;
	private int keys_n;
	//the load factor, α
	private int alpha;
	//index of the prime we are using
	private int prime_ind;
	private ArrayList<HashElem<K, V>> hash_table;
	private static int[] prime_array = {11, 19, 41, 79, 163, 317, 641, 1279, 2557, 5119, 10243, 20479, 40961, 81919, 163841, 327673};
	//Key: name
	//value: phone number
	public int makeHash(K obj, int prime_loc){
		return Math.abs(obj.hashCode()%prime_array[prime_loc]);
	}

	public HashTable(){
		hash_table = new ArrayList<HashElem<K, V>>(prime_array[0]);
		int i;
		for(i = 0; i < prime_array[0]; i++){
			hash_table.add(i, null);
		}
		System.out.println("prime_array[0] " + prime_array[0]);
		System.out.println("Arraylist size " + hash_table.size());
		size_m = prime_array[0];
		prime_ind = 0;
	}
	//maps the key to a value in the table
	public void put(K key, V value){
		int tab_key = Math.abs(key.hashCode()%size_m);
		/*System.out.println("\nkey: " + key);
		System.out.println("hashcode: " + key.hashCode());
		System.out.println("index: " + tab_key);
		System.out.println("size: " + size_m);*/
		HashElem<K, V> put_elem;
		HashElem<K, V> temp_elem;
		if(this.loadFactor() > 0.75){
			this.reHash();
		}
		//if there is already an element at that index
		if(hash_table.get(tab_key) != null){
			temp_elem = hash_table.get(tab_key);
			//variable that tells us what to do in various cases
			int cond = 0;
			//start null, and change if we need to set it to something else
			HashElem next_elem = null;
			//go down the chain until we find the element
			while(temp_elem != null){
				//if we are putting in an element we already have in the table
				if(key == temp_elem.getKey()){
					//we have found our element, so break the loop
					cond = 1;
					break;
				}
				temp_elem = temp_elem.getNext();
			}
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
			hash_table.add(tab_key, put_elem);
		}
	}
	//gets the value associated with a certain key
	public V get(K key){
		int tab_index = Math.abs(key.hashCode()%size_m);
		HashElem<K, V> temp_elem = hash_table.get(tab_index);
		while(temp_elem != null){
			//if this is the key
			if(temp_elem.getKey() == key){
				//return the value
				return temp_elem.getValue();
			}
			//keep looking down the chain
			temp_elem = temp_elem.getNext();
		}
		else{
			return null;
		}
	}
	//checks whether a key is valid in the table
	public boolean contains(K key){
		int temp_hash = makeHash(key, prime_ind);
		if(hash_table.get(temp_hash) != null){
			return true;
		}
		else{
			return false;
		}
	}
	//deletes an element at a certain key
	public void delete(K key){

	}
	public int getSize(){
		return size_m;
	}
	//calculates the load factor α = n/m
	public double loadFactor(){
		return (double)keys_n/(double)size_m;
	}
	//rehashes the table with the next prime
	public void reHash(){
		int i;
		int new_hash;
		HashElem re_elem;
		K re_key;
		V re_value;
		//store the old table
		ArrayList old_table = hash_table;
		//increment to and get the next prime
		prime_ind++;
		size_m = prime_array[prime_ind];
		System.out.println("-----------REHASHING: NEW SIZE----------: " + size_m);
		//reassign our new hash table arraylist
		hash_table = new ArrayList<HashElem<K, V>>(size_m);
		for(i = 0; i < size_m; i++){
			hash_table.add(i, null);
		}
		//go through everything in the old table and rehash
		for(i = 0; i < size_m; i++){
			if(hash_table.get(i) != null){
				re_elem = hash_table.get(i);
				while(re_elem != null){
					//get the element's key and value
					re_key = hash_table.get(i).getKey();
					re_value = hash_table.get(i).getValue();
					//put the element in the new hash table
					this.put(re_key, re_value);
					//check if the chain has next elements
					//continue rehashing if it does
					re_elem = re_elem.getNext();
				}
			}
		}
	}
}
