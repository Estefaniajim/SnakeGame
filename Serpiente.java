
import java.util.NoSuchElementException;
 // linked list of the snake

public class Serpiente {
	private int tama�o;
	private nodoSerpiente cabeza,
							cola;
	public Serpiente() {
		this.cabeza=this.cola=null;
		this.tama�o=0;
	}
	public int size() {
		return this.tama�o;
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
		 if(tama�o == 0) {
			 this.cola=nvo;
		 }
		 cabeza=nvo;
		 this.tama�o++;
	 }
	
	public void insertAtLast(int pos) {
		nodoSerpiente nuevo = new nodoSerpiente(pos, null);
		if(tama�o == 0) {
			 this.insertFirst(pos);
			 
		 }else {
			 nodoSerpiente nvo= new nodoSerpiente(pos, null);
			 this.cola.next = nvo ;//this.last.next=nvo
			 this.cola=nvo;
			 this.tama�o++;
		 }
	
	}
	
	public int removeFirst() {
		 if(!isEmpty()) {
			 nodoSerpiente tmp=this.cabeza;
			 this.cabeza=this.cabeza.next;
			 this.tama�o--;
			 return tmp.pos; 
		 }else {
			 throw new NoSuchElementException("Error");
		 }
	}
	
	public int removeLast() throws NoSuchElementException {
		try {
		if(this.tama�o<=1) {
			return this.removeFirst();
		}else {
		int dato=this.cola.pos;
		nodoSerpiente current=this.cabeza;
		for(int i=0; i<this.tama�o-1; i++) {
			current=current.next;
		}
		current.next = null;
		this.cola=current;
		this.tama�o--;
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
