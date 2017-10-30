import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
/**
This class functions to be the element stored at the 
array indices of the hash table. This object can store 
key-value pairs in the hash table.
*/
public class HashElem<K, V>{
	private HashElem next;
	private HashElem prev;
	private K key;
	private V value;
	public HashElem(K k, V v, HashElem p, HashElem n){
		key = k;
		value = v;
		next = n;
		prev = p;
	}
	public K getKey(){
		return key;
	}
	public V getValue(){
		return value;
	}
	public HashElem getPrev(){
		return prev;
	}
	public HashElem getNext(){
		return next;
	}
	public void setNext(HashElem next_elem){
		this.next = next_elem;
	}
	public void setPrev(HashElem prev_elem){
		this.prev = prev_elem;
	}
	public void setValue(V v){
		value = v;
	}
}