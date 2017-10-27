import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

public class HashTable<K, V>{
	private int size;

	//Key: name
	//value: phone number
	//takes the word we want to map and returns the array index of that word
	public int hashFunction(K obj){
		return obj.hashCode();
	}





	public HashTable(ArrayList<HashElem> list){
		for(int i = 0; i < list.size(); i++){

		}
	}
	//
	public void put(int key, int value){

	}
	public int get(key){

	}
	public boolean contains(int key){

	}
	public void delete(key){

	}
	public int size(){
		return this.size;
	}
}