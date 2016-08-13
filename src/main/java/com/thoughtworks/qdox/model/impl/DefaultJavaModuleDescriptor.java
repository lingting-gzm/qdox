package com.thoughtworks.qdox.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.thoughtworks.qdox.model.JavaModuleDescriptor;

public class DefaultJavaModuleDescriptor implements JavaModuleDescriptor
{

    private Collection<DefaultJavaExports> exports = new ArrayList<DefaultJavaExports>();

    private Collection<DefaultJavaRequires> requires = new ArrayList<DefaultJavaRequires>();

    private Collection<DefaultJavaUses> uses = new ArrayList<DefaultJavaUses>();

    private Collection<DefaultJavaProvides> provides = new ArrayList<DefaultJavaProvides>();

    public void addExports( DefaultJavaExports exports )
    {
        this.exports.add( exports );
    }

    public Collection<JavaExports> getExports()
    {
        return Collections.<JavaExports>unmodifiableCollection( exports );
    } 

    public void addRequires( DefaultJavaRequires requires )
    {
        this.requires.add( requires );
    }

    public Collection<JavaRequires> getRequires()
    {
        return Collections.<JavaRequires>unmodifiableCollection( requires );
    }

    public void addProvides( DefaultJavaProvides provides )
    {
        this.provides.add( provides);
    }

    public Collection<JavaProvides> getProvides()
    {
        return Collections.<JavaProvides>unmodifiableCollection( provides );
    }

    public void addUses( DefaultJavaUses uses)
    {
        this.uses.add( uses );
    }

    public Collection<JavaUses> getUses()
    {
        return Collections.<JavaUses>unmodifiableCollection( uses );
    } 

    public static class DefaultJavaExports extends AbstractJavaModel implements JavaModuleDescriptor.JavaExports
    {
        private String source;

        private Collection<String> modifiers;

        private Collection<String> targets;

        public DefaultJavaExports( String source, Collection<String> modifiers, Collection<String> targets )
        {
            this.source = source;
            this.modifiers = modifiers;
            this.targets = targets;
        }

        public String getSource()
        {
            return source;
        }

        public Collection<String> getTargets()
        {
            if( targets == null )
            {
                return Collections.emptyList();
            }
            else
            {
                return targets;
            }
        }

        public boolean isDynamic()
        {
            return getModifiers().contains( "dynamic" );
        }

        public Collection<String> getModifiers()
        {
            if( modifiers == null )
            {
                return Collections.emptyList();
            }
            else
            {
                return modifiers;
            }
        }

        public String getCodeBlock()
        {
            return getModelWriter().writeModuleExports( this ).toString();
        }
    }
    
    public static class DefaultJavaProvides extends AbstractJavaModel implements JavaModuleDescriptor.JavaProvides
    {
        private String service;
        
        private String provider;

        public DefaultJavaProvides( String service, String provider )
        {
            super();
            this.service = service;
            this.provider = provider;
        }
        
        public String getService()
        {
            return service;
        }
        
        public String getProvider()
        {
            return provider;
        }
        
        public String getCodeBlock()
        {
            return getModelWriter().writeModuleProvides( this ).toString();
        }
    }
    
    public static class DefaultJavaRequires extends AbstractJavaModel implements JavaModuleDescriptor.JavaRequires 
    {
        private String name;
        
        private Collection<String> modifiers;
    
        public DefaultJavaRequires( String name, Collection<String> modifiers )
        {
            this.name = name;
            this.modifiers = modifiers;
        }
    
        public String getName()
        {
            return name;
        }
    
        public boolean isPublic()
        {
            return getModifiers().contains( "public" );
        }
    
        public boolean isStatic()
        {
            return getModifiers().contains( "static" );
        }
    
        public Collection<String> getModifiers()
        {
            if( modifiers == null )
            {
                return Collections.emptyList();
            }
            else
            {
                return modifiers;
            }
        }
        
        public String getCodeBlock()
        {
            return getModelWriter().writeModuleRequires( this ).toString();
        }
    }
    
    public static class DefaultJavaUses extends AbstractJavaModel implements JavaModuleDescriptor.JavaUses
    {
        private String name;

        public DefaultJavaUses( String name )
        {
            this.name = name;
        }
        
        public String getName()
        {
            return name;
        }

        public String getCodeBlock()
        {
            return getModelWriter().writeModuleUses( this ).toString();
        }
    }
}
