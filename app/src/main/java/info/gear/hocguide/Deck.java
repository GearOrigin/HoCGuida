package info.gear.hocguide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deck {

    private ArrayList<Team> teams=new ArrayList<Team>();

    public Deck()
    {

    }

    public void add(Team team)
    {
        if(teams.size()<3)
        {
            teams.add(team);
        }
    }


    public void remove(int position)
    {
        if(teams.size()>0) {
            teams.remove(position);
        }
    }


    public int getPartyATK()
    {
        int ATK=0;

        for(int i=0;i<teams.size();i++)
        {
            ATK+=teams.get(i).getATK();
        }


        return ATK;
    }

    public int getPartyHP()
    {
        int HP=0;

        for(int i=0;i<teams.size();i++)
        {
            HP+=teams.get(i).getHP();
        }
        return HP;
    }

    public int getPartyMana()
    {
        int mana=0;

        for(int i=0;i<teams.size();i++)
        {
            mana+=teams.get(i).getMana();
        }

        return mana;
    }

    public Team getTeam(int position)
    {
        return teams.get(position);
    }

    public ArrayList<Team> getParty()
    {
        return teams;
    }

    public boolean containOnDeck(Carta c)
    {
        boolean trovato=false;
        for (Team t: teams)
        {
            for(int i=0;i<4;i++)
            {
                if (t.getCard(i)!=null && t.getCard(i).getNome().equalsIgnoreCase(c.getNome()))
                {
                    trovato=true;
                    return trovato;
                }
            }
        }
        return trovato;
    }

    public int getMinStelle()
    {
        int bestMin=0;
        for(Team t: teams)
        {
         if(t.teamCompleto()) {
             for (int i = 1; i <4;i++)
             {
                 if(t.getCard(i-1).getStelle()<=t.getCard(i).getStelle())
                 {
                     if(t.getCard(i-1).getStelle()<=bestMin) {
                         bestMin = t.getCard(i-1).getStelle();
                     }

                 }
                 else
                 {
                     if(t.getCard(i).getStelle()<=bestMin)
                     {
                         bestMin=t.getCard(i).getStelle();
                     }
                 }
             }
         }
           else
         {
             return 0;
         }
        }

        return bestMin;
    }

    public int getMaxStelle()
    {
        int bestMax=0;
        for(Team t: teams)
        {
            if(t.teamCompleto()) {
                for (int i = 1; i <4;i++)
                {
                    if(t.getCard(i-1).getStelle()>=t.getCard(i).getStelle())
                    {
                        if(t.getCard(i-1).getStelle()>=bestMax) {
                            bestMax = t.getCard(i-1).getStelle();
                        }

                    }
                    else
                    {
                        if(t.getCard(i).getStelle()>=bestMax)
                        {
                            bestMax=t.getCard(i).getStelle();
                        }
                    }
                }
            }
            else
            {
                return 0;
            }
        }

        return bestMax;
    }


    public ArrayList<Carta> getAllCardses()
    {
        ArrayList<Carta> result=new ArrayList<Carta>();

        for(Team t: teams)
        {
            for(Carta c:t.getAllCards())
            {
                result.add(c);
            }
        }

        return result;
    }
}
