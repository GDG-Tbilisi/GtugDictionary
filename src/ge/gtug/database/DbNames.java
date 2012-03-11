package ge.gtug.database;

class DbNames {
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

	