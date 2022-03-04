package org.ob.data.model.tokens;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Objects;

@NodeEntity
public class Token {

    @Id
    public String access_token;

    @Index
    public Long expires_in;

    @Property
    public String token_type;

    @Property
    public String scope;

    @Property
    public String refresh_token;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Token token = ( Token ) o;
        return Objects.equals( access_token, token.access_token );
    }

    @Override
    public int hashCode() {
        return Objects.hash( access_token );
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token( String access_token ) {
        this.access_token = access_token;
    }

    public static class Builder{

        private String access_token;

        public Token.Builder access_token( String access_token ) {
            this.access_token = access_token;
            return this;
        }

        public Token build() {

            Token t = new Token();
            t.setAccess_token( access_token );

            return t;

        }

    }


}
