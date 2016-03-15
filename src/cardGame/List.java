package cardGame;
public class List<T> implements ListInterface<T>{
	
	node HEAD;
	node TAIL;
	
	class node<T>{
		node next,prev;
		T val;
		public node(node n,node p,T v){
			next=n;
			prev=p;
			val=v;
		}
	}
	
	public List () {
		HEAD=new node(null,null,null);
		TAIL=new node(null,null,null);
	}
	@Override
	public void add(T newEntry) {
		node tmp = new node(null,null,newEntry);
		if(HEAD.next!=null){
			tmp.next=TAIL;
			tmp.prev=TAIL.prev;
			tmp.prev.next=tmp;
			TAIL.prev=tmp;
		}else{
			HEAD.next=tmp;
			TAIL.prev=tmp;
		}
	}

	@Override
	public boolean add(int newPosition, T newEntry) {
		node next = HEAD.next;
		int i=0;
		if(newPosition==0){
			node tmp= new node(null,null,newEntry);
			tmp.next=HEAD.next;
			HEAD.next=tmp;
			tmp.prev=HEAD;
			tmp.next.prev=tmp;
		}
		while(next.next!=null){
			if(i==newPosition-1){
				node tmp1=next;
				node tmp2=next.next;
				node tmp= new node(tmp2,tmp1,newEntry);
				
				tmp1.next=tmp;
				
				tmp2.prev=tmp;
				return true;
			}
			++i;
			next=next.next;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(int givenPosition) {
		node next = HEAD.next;
		int i=0;
		if(givenPosition==0){
			node tmp=HEAD.next;
			HEAD.next=tmp.next;
			tmp.next.prev=HEAD;
			return (T) tmp.val;
		}
		while(next.next!=null){
			if(i==givenPosition){
				node tmp1=next.prev;
				node tmp2=next.next;
				tmp1.next=tmp2;
				tmp2.prev=tmp1;
				return (T) next.val;
			}
			++i;
			next=next.next;
		}
		return null;
	}

	@Override
	public void clear() {
		HEAD.next=null;
		TAIL.prev=null;
	}

	@Override
	public boolean replace(int givenPosition, T newEntry) {
		node next = HEAD.next;
		int i=0;
		if(givenPosition==0){
			HEAD.next.val=newEntry;
			return true;
		}
		while(next.next!=null){
			if(i==givenPosition-1){
				
				next.next.val=newEntry;
				return true;
			}
			++i;
			next=next.next;
		}
		return false;
	}

	@Override
	public T getEntry(int givenPosition) {
		node next = HEAD.next;
		int i=0;
		if(givenPosition==0){
			return (T) HEAD.next.val;
		}
		while(next.next!=null){
			if(i==givenPosition-1){
				
				return (T) next.next.val;
			}
			++i;
			next=next.next;
		}
		return null;
	}

	@Override
	public boolean contains(T anEntry) {
		node next = HEAD.next;
		if(next==null)return false;
		while(next.next!=null){
				if(next.val.equals(anEntry))return true;
			next=next.next;
		}
		return false;
	}

	@Override
	public int getLength() {
		node next = HEAD.next;
		int i=0;
		
		// if there is only one element
		if((next != null) && (next.next == null))
			i = 1;
		
		while(next.next!=null){
			++i;
			next=next.next;
		}
		return i;
	}

	@Override
	public boolean isEmpty() {
		if(HEAD.next==null)return true;
		return false;
	}
	@Override
	public void display() {
		node next = HEAD.next;
		if(next.next == null)
			System.out.println(next.val.toString());
		while(next.next!=null){
			System.out.println(next.val.toString());
			next=next.next;
		}
		// TODO Auto-generated method stub
		
	}

}
