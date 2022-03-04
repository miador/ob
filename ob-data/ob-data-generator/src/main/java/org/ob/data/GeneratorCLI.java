package org.ob.data;

import picocli.CommandLine;

@CommandLine.Command( name = "GeneratorCLI", version = "GeneratorCLI 1.0-SNAPSHOT", mixinStandardHelpOptions = true )
public class GeneratorCLI implements Runnable {

    @CommandLine.Option( names = { "-c", "--customer" }, description = "Number of customers" )
    Integer customer = 1;

    @CommandLine.Option( names = { "-a", "--account" }, description = "Number of accounts" )
    Integer account =  1;

    @CommandLine.Option( names = { "-t", "--transaction" }, description = "Number of transactions" )
    Integer transaction = 10;

    @CommandLine.Option( names = { "-co", "--contact" }, description = "Number of contacts" )
    Integer contact = 10;

    @CommandLine.Option( names = { "-uri" }, description = "Uri for Neo4j" )
    String uri = "bolt://localhost:7687";

    @CommandLine.Option( names = { "-u", "--username" }, description = "Username for Neo4j" )
    String n_username = "neo4j";

    @CommandLine.Option( names = { "-p", "--password" }, description = "Password for Neo4j" )
    String n_password = "<z),JL=zRZE6k4fC";

    public static void main( String... args ) {
        new CommandLine( new GeneratorCLI() ).execute( args );
    }

    @Override
    public void run() {

        var start = System.currentTimeMillis();

        GraphGenerator.init( uri, n_username, n_password );

        for ( int i = 0; i < customer; i++ ) {

            GraphGenerator.generate( account, transaction, contact );
            render( i, customer );

        }

        var end = (System.currentTimeMillis() - start)/1000;

        System.out.println("\nElaspsed time: " + end + " seconds");
        System.exit( 0 );

    }

    private static void render( int i, int P ) {

        var progress = ( ( ( double ) ( i + 1 ) / P ) * 100 );

        var plus    = "████████████████████████████████████████████████████████████████████████████████████████████████████";
        var minus   = "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░";

        var bar = plus.substring( 0, ( int ) progress ) + minus.substring( ( int ) progress, 100 );

        System.out.print( bar + " " + String.format( "%.1f", progress ) + "%\r" );

    }

}
