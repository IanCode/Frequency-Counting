import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

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
}