package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button[][] btn=new Button[3][3];
TextView TXTj1,TXTj2;
   private boolean joueur1=true;// X
   private int Rcount,Pointjoeur1=0,Pointjoeur2=0; // Rcount pour savoir si le jeu est fini Round=9


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TXTj1=(TextView) findViewById(R.id.joueur1);
        TXTj2=(TextView) findViewById(R.id.joueur2);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               String buttonid="g_"+i+j;
               int resource_id=getResources().getIdentifier(buttonid,"id",getPackageName());
               btn[i][j]=findViewById(resource_id);
                btn[i][j].setOnClickListener(this);
            }// fin for j

        }// fin for i
        Button refresh=(Button)findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             rejouer();

            }
        });
    }// fin oncreate

    private void rejouer() {
        Pointjoeur1=0;
        Pointjoeur2=0;
        MAJ();
        RESET();
    }// fin rejouer

    @Override
    public void onClick(View v) {
        if( !((Button) v).getText().toString().equals("") ){ // pour savoir si mon btn est vide non utiliser

            return;
        }// fin if button
        if(joueur1){
            ((Button) v).setText("x");

           // Toast.makeText(this, "j1", Toast.LENGTH_SHORT).show();

        }// fin if joeur1 X
        else { // joueur2 O
            ((Button) v).setText("O");

          //  Toast.makeText(this, "j2", Toast.LENGTH_SHORT).show();
        }// fin joeur2 O
        Rcount++;
        if(gagnant()){// si true
            if(joueur1){
                joeur1gagne();
            }else {
                joeur2gagne();
            }}// fin if gagnant
            else if(Rcount == 9){
                draw();
            }else {
                joueur1=!joueur1;
            }



    }// fin Onclick

    private void draw() {
        Toast.makeText(this, "FAILLE! \uD83D\uDE28 ", Toast.LENGTH_LONG).show();
        RESET();
    }

    private void joeur2gagne() {
        Pointjoeur2++;
        Toast.makeText(this, "\uD83C\uDF89 JOEUR 2 A GAGNÉ \uD83C\uDF89", Toast.LENGTH_LONG).show();
        MAJ();
        RESET();
    }

    private void joeur1gagne() {
        Pointjoeur1++;
        Toast.makeText(this, "\uD83C\uDF89 JOEUR 1 A GAGNÉ \uD83C\uDF89", Toast.LENGTH_LONG).show();
        MAJ();
        RESET();
    }

    private void RESET() {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                btn[i][j].setText("");
            }// fin for j
        }// fin for i
        Rcount=0;
        joueur1=true;
    }// fin reset

    private void MAJ() {
        TXTj1.setText("Joueur 1 : "+Pointjoeur1);
        TXTj2.setText("Joueur 2 : "+Pointjoeur2);
    }// mis a jour fin round

    private boolean gagnant(){
        String[][] check=new  String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
             check[i][j]=btn[i][j].getText().toString() ;
            }// fin for j

        }// fin for i
/******************************************************** -R&C- ********************************************************************/
        for(int i=0;i<3;i++){
            if( check[i][0].equals(check[i][1]) && check[i][0].equals(check[i][2]) && !(check[i][0].equals("")) ){
            return true;
            }// fin if1
        }// fin for i 1 ligne par ling

        for(int i=0;i<3;i++){
            if( check[0][i].equals(check[1][i]) && check[0][i].equals(check[2][i]) && !(check[0][i].equals("")) ){

                return true;
            }// fin if1
        }// fin for i 2 colomn par colomn
/*********************************************************** -DIAGONALE- *****************************************************************/

        if( check[0][0].equals(check[1][1]) && check[0][0].equals(check[2][2]) && !(check[0][0].equals("")) ){

            return true;
        }// fin if diagonale droite gauche
        if( check[0][2].equals(check[1][1]) && check[0][2].equals(check[2][0]) && !(check[0][2].equals("")) ){

            return true;
        }// fin if diagonale gauche droite

        return false;
    }// fin gagnant

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Rcount=savedInstanceState.getInt("Rcount");
        Pointjoeur1=savedInstanceState.getInt("Pointjoeur1");
        Pointjoeur2=savedInstanceState.getInt("Pointjoeur2");
        joueur1=savedInstanceState.getBoolean("joueur1");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Rcount",Rcount);
        outState.putInt("Pointjoeur1",Pointjoeur1);
        outState.putInt("Pointjoeur2",Pointjoeur2);
        outState.putBoolean("joeur1",joueur1);

    }
}// fin class
