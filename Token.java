public class Token
{
  private Symbol sym; 
  private String id;
  private long num;

  public Token(Symbol s, String i, long n)
  {
    sym = s;
    id = i;
    num = n;
  }

  public Symbol getSym()
  {
    return sym;
  }

  public String getId()
  {
    return id;
  }

  public long getNum()
  {
    return num;
  }

  public void setSym(Symbol s)
  {
    sym = s;
  }

  public void setId(String i)
  {
    id = i;
  }

  public void setNum(long n)
  {
    num = n;
  }

  public String toString()
  {
    return ("Symbol: " + sym + ", id: " + id + ", num: " + num); 
  }
}
