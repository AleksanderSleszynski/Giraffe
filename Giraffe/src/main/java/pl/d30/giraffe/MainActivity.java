package pl.d30.giraffe;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    private String[] quotes;
    private static final Random rgenerator = new Random();
    private String[] color = {"#33B5E5", "#AA66CC", "#99CC00", "#FFBB33", "#FF4444", "#0099CC", "#9933CC", "#669900", "#FF8800","#CC0000"};


    private TextView quote;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quote = (TextView) findViewById(R.id.text);
        author = (TextView) findViewById(R.id.author);

        setNewQuote();
    // TODO: Nastepny swipe w prawo poprzedni swipe w lewo klikniecie na cytat losowanie nastepnego
    // TODO: Zrobić 10 ostatnich zapamietanych przy uzyciu listy
        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNewQuote();
            }
        });
    }

    //TODO: teraz zrób tak, żeby nie losował się ten sam string dwa razy pod rząd a najlepiej tak, że
    //TODO: robisz sobie listę [1,2,3,..., 10] z każdym nowym wylosowaniem dodajesz do tej listy

    /* pseudokod:

     a = []
    def wylosuj():
        while( wylosuj in a);
        return wolosowany

     cytat = wylosuj()
     a.push(cytat)
     if( a.length>10) usun_pierwszy_element( a )

     wstaw_w_odpowiednie_miejsce(cytat)
     // good luck :)
     */

    private void setNewQuote() {
        String[] temp = quoteRandom();

        if( temp.length==1 || temp[0].equals("") ) {
            author.setText("Anonymous");
            quote.setText( temp[ temp.length==1 ? 0 : 1 ] );

        } else {
            author.setText( temp[0] );
            quote.setText( temp[1] );
        }

        int c = Color.parseColor( color[rgenerator.nextInt(color.length)] );

        author.setTextColor(c);
        quote.setTextColor(c);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private String[] quoteRandom(){
        Resources res = getResources();
        quotes = res.getStringArray(R.array.quotes);
        String q = quotes[rgenerator.nextInt(quotes.length)];
        return q.split("\\|");

    };
}
