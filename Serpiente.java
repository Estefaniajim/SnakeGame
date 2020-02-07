
import java.util.NoSuchElementException;
 // linked list of the snake

public class Serpiente {
	private int tamaño;
	private nodoSerpiente cabeza,
							cola;
	public Serpiente() {
		this.cabeza=this.cola=null;
		this.tamaño=0;
	}
	public int size() {
		return this.tamaño;
	}
	
	public boolean isEmpty() {
		return this.cabeza==null;
	}
	
	public nodoSerpiente cabeza() {
		return this.cabeza;
	}
	public nodoSerpiente cola() {
		return this.cola;
	}
	
	 public void insertFirst(int pos) {
		 nodoSerpiente nvo= new nodoSerpiente (pos,this.cabeza);
		 if(tamaño == 0) {
			 this.cola=nvo;
		 }
		 cabeza=nvo;
		 this.tamaño++;
	 }
	
	public void insertAtLast(int pos) {
		nodoSerpiente nuevo = new nodoSerpiente(pos, null);
		if(tamaño == 0) {
			 this.insertFirst(pos);
			 
		 }else {
			 nodoSerpiente nvo= new nodoSerpiente(pos, null);
			 this.cola.next = nvo ;//this.last.next=nvo
			 this.cola=nvo;
			 this.tamaño++;
		 }
	
	}
	
	public int removeFirst() {
		 if(!isEmpty()) {
			 nodoSerpiente tmp=this.cabeza;
			 this.cabeza=this.cabeza.next;
			 this.tamaño--;
			 return tmp.pos; 
		 }else {
			 throw new NoSuchElementException("Error");
		 }
	}
	
	public int removeLast() throws NoSuchElementException {
		try {
		if(this.tamaño<=1) {
			return this.removeFirst();
		}else {
		int dato=this.cola.pos;
		nodoSerpiente current=this.cabeza;
		for(int i=0; i<this.tamaño-1; i++) {
			current=current.next;
		}
		current.next = null;
		this.cola=current;
		this.tamaño--;
		return dato;
		}
		}
		catch(NoSuchElementException ex) {
			throw new NoSuchElementException("Error");
		}
	}
	
}
// nodes of the snake 
class nodoSerpiente{
	int pos; //the position that the node has 
	nodoSerpiente next; // next node
	
	nodoSerpiente(int pos, nodoSerpiente next){
		this.pos=pos;
		this.next=next;
	}
	nodoSerpiente(){
		this.pos=0;
		this.next=null;
	}

}
