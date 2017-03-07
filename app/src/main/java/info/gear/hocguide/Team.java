package info.gear.hocguide;

import java.util.ArrayList;

public class Team {

     private Carta[] team;




    public Team()
    {
        team= new Carta[4];
    }

    public void add(Carta card,int posizione)
    {
        team[posizione]=card;
    }
    public void removeCarta(int posizione)
    {
        if(team[posizione]!=null)
        {
            team[posizione]=null;
        }

    }

    public int getATK()
    {
      int ATK=0;

      for(int i=0;i<4;i++)
      {
         if (team[i]!=null) ATK+=team[i].bestAtk();
      }
        return ATK;

    }

    public int getHP()
    {
        int HP=0;

        for(int i=0;i<4;i++)
        {
            if (team[i]!=null) HP+=team[i].bestHp();
        }

        return HP;

    }

    public int getMana()
    {
        int mana=0;

        for(int i=0;i<4;i++)
        {
            if (team[i]!=null) mana+=team[i].getMana();
        }

        return mana;
    }

    public Carta getCard(int posizion)
    {
        if (team[posizion]!=null)
            return team[posizion];
        return null;
    }

    public Carta[] getAllCards()
    {
        return team;
    }

    public ArrayList<Carta> getAllCardToArr()
    {
        ArrayList<Carta> result=new ArrayList<Carta>();

        for(Carta c : team)
        {
            result.add(c);
        }

        return result;
    }

   public int getStelleTot()
   {
       int trovato=0;

       if(team.length==4) {
           for (int i = 1; i < team.length; i++) {
               if (team[i].getStelle() != team[i - 1].getStelle()) {
                   trovato = 1;
               }
           }

           if (trovato == 0)
           {
              return team[0].getStelle();
           }

           }

       return 0;


       }

    public boolean teamCompleto()
    {
        if(team.length==4)
        {
            if(team[0]!=null && team[1]!=null && team[2]!=null && team[3]!=null)
            {
                return true;
            }
        }

        return false;
    }

    public char tipoTeam()
    {
        if(teamCompleto())
        {
            for(int i=1;i<team.length;i++)
            {
                 if(team[i].getTipo()==R.drawable.demonlogo || team[i].getTipo()!=team[i-1].getTipo())
                 {
                     return 'N';
                 }
            }

            if(team[0].getTipo()==R.drawable.druidlogonew) return 'D';
            if(team[0].getTipo()==R.drawable.camelotlogonew) return 'C';
        }

        return 'N';
    }


}
