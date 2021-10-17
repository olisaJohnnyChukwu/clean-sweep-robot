package graph;

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
    }
    
    public Graph(int V){
        this.V=V;
        adj= new HashSet[V];
        for(int i=0;i<V;i++){ 
            adj[i]=new HashSet<Integer>();
        }
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
    
    
    
    
	public static void main(String[] args) {
			
		
	}

}
