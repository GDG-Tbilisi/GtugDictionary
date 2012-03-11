package ge.gtug.database;

public class DbNames {
	 public static enum View {
		    TABLE ("DicV"),
		    GEO_Word    ("geo"),
		    ENG_WORD  ("eng");
		    
		    private String value;
		        
		    View(String value) {
		      this.value = value;
		    }
		    
		    @Override 
		    public String toString() {
		      return value;
		    }
		  }
		  
	  public static enum Geo {
	    TABLE ("geo"),
	    ID    ("id"),
	    WORD  ("geo"),
	    TYPE  ("type");
	    
	    private String value;
	        
	    Geo(String value) {
	      this.value = value;
	    }
	    
	    @Override 
	    public String toString() {
	      return value;
	    }
	  }
	  
	  public static enum Eng {
	    TABLE         ("eng"),
	    ID            ("id"),
	    WORD          ("eng"),
	    TYPE          ("type"),
	    TRANSCRIPTOPN ("transcription");
	    
	    private String value;
	            
	    Eng(String value) {
	      this.value = value;
	    }
	    
	    @Override 
	    public String toString() {
	      return value;
	    }
	  }
	  
	  public static enum Types {
	    TABLE ("types"),
	    ID    ("id"),
	    NAME  ("name"),
	    ABBR  ("abbr");
	    
	    private String value;
	    
	    Types(String value) {
	      this.value = value;
	    }
	    
	    @Override 
	    public String toString() {
	      return value;
	    }
	  }
	}

	