package CleanSweep.src.main.java.graph;

import java.util.*;

public class Graph {
	private boolean[] marked; 
    private final int V;
    int e;
    public HashSet<Integer>[] adj;
    private HashSet<Integer> vertices=new HashSet<Integer>();
	
    public Graph(){
    	this.V=100;
        adj= new HashSet[V];
        for(int i=0;i<V;i++){
            adj[i]=new HashSet<Integer>();
        }
        //marked = new boolean[this.V];
    }
    
    public Graph(int V){
        this.V=V;
        adj= new HashSet[V];
        for(int i=0;i<V;i++){ 
            adj[i]=new HashSet<Integer>();
        }
       // marked = new boolean[this.V];
    }
    
    public void addedge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        vertices.add(v);
        vertices.add(w);
        e++;
    }
    
    public HashSet<Integer> neighbours(int v){

    	HashSet<Integer> l=adj[v];

        return l;
    }
	
    public Queue<Integer> dfs(Graph g,int v){
        
        marked = new boolean[g.V];
        
        Queue<Integer> queue=new LinkedList<Integer>();
        
        dfs2(g, v,queue);
        
        return queue;
        
    }
    
   
    private void dfs2(Graph g,int v, Queue<Integer> queue){

        marked[v]=true;
        
        for(int i:g.adj[v]){
        
            if(!marked[i]){
            
               
               
                	queue.add(i);
                
                dfs2(g, i ,queue);
                
            }
            
            
        }
        
    }
    public Graph reverse(Graph g){
    	Graph R=new Graph(V);
        HashSet<Integer> set=g.vertices;

        for(Integer i:set){
        
            for(Integer j:adj[i]){
            
                R.addedge(j, i);
            }
        }
        return R;
    }
    
    
   public void bfs(int v) {
	   marked = new boolean[this.V];
	   Queue<Integer> queue=new LinkedList<Integer>();
	   marked[v]=true;
	   queue.add(v);
	   
	   while(!queue.isEmpty()) {
		   
		   int x=queue.poll();
		   
		   for(int i:this.adj[x]) {
			   if(!marked[i]) {
				   marked[i]=true;
				   
				   queue.add(i);
				   System.out.println(i);
			   }
			  
		   }
	   }
	   
	   
	   
   }
   
   
   /*public boolean hasPathTo(int v) {
	   return marked[v];
   }
    
   
   public Iterable<Integer> pathTo(int v){
	   if( !hasPathTo(v)) return null;
	   Stack<Integer> path=new Stack<>();
	   
	   for(v:this.) {
		   
	   }
   }*/
    
   public Iterable<Integer> getPath(int s, int d){
	   DepthFirstSearchPaths DepthFirstSearchPaths=new DepthFirstSearchPaths(this,s);
	   
	   return DepthFirstSearchPaths.pathTo(d);
   }
   public class DepthFirstSearchPaths{
	   private boolean[] marked; 
	   private final int V;
	   int s;
	   Graph g;
	   private int[] edgeTo;
	   
	public DepthFirstSearchPaths(Graph g,int s) {
		
		this.g=g;
		this.edgeTo=new int[g.V];
		this.marked = new boolean[g.V];
		this.s=s;
		this.V=g.V;
		this.dfs(g,s);
	}
	   
	private void dfs(Graph g,int v) {
		marked[v]=true;
		
		for(int i:g.neighbours(v)) {
			if(!marked[i]) {
				edgeTo[i]=v;
				dfs(g,i);
			}
		}
		
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if( !hasPathTo(v)) return null;
		Stack<Integer> path=new Stack<>();
		
		for(int i=v;i!=s;i=edgeTo[i]) {
			path.push(i);
		}
		path.push(s);
		return path;
	}
	   
   }
   
    
   
	public static void main(String[] args) {
		Graph graph=new Graph();
        graph.addedge(1, 2);
        graph.addedge(2,3);
        graph.addedge(2,4);
        graph.addedge(1,3);
        graph.addedge(2,6);
        graph.addedge(6,7);
        /*graph.dfs(graph, 1).stream().forEach(s->{
            System.out.println(s);
        });*/

        
       // graph.bfs(1);
        
        
        System.out.println(graph.getPath(1, 7));
        
        
       
        
        
		
	}

}
