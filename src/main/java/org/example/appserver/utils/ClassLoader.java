package org.example.appserver.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClassLoader {
    public Set<Class<?>> loadAllClassesInClassPath(String classPath) {
        Set<Class<?>> result = new HashSet<>();
        loadAllClassesInClassPath( classPath, result );
        return result;
    }

    private void loadAllClassesInClassPath(String classPath, Set<Class<?>> classes) {
        File pkgF = new File( classPath );
        for ( File fileEntry : Objects.requireNonNull( pkgF.listFiles() ) ) {
            if ( fileEntry.isDirectory() ) {
                loadAllClassesInClassPath( fileEntry.toString(), classes );
            } else {
                try {
                    String path = fileEntry.toString().substring( fileEntry.toString().indexOf( "org" ) );
                    Class<?> clazz = Class.forName( FilenameUtils.removeExtension( path.replace( '/', '.' ) ) );
                    classes.add( clazz );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
