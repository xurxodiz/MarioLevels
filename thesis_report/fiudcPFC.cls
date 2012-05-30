% Definicion de la plantilla
\NeedsTeXFormat{LaTeX2e}[1995/12/01]
\ProvidesClass{fiudcPFC}[2010/07/08 LIDIA Plantilla para proyectos]

% Informacion de salida
\typeout{Autores: Juan A. Suarez Romero y Oscar Fontenla Romero}
\typeout{Proyecto Fin de Carrera}
\typeout{Facultad de Informatica}
\typeout{Universidad de A Corunna}
\typeout{---}
\typeout{$Id$}
\typeout{}

% Definimos el idioma inicialmente vacio
\def\@idioma{\empty}

% Declaramos las opciones que podemos pasar
\DeclareOption{10pt}{\PassOptionsToClass{10pt}{book}}
\DeclareOption{11pt}{\PassOptionsToClass{11pt}{book}}
\DeclareOption{12pt}{\PassOptionsToClass{12pt}{book}}
\DeclareOption{openany}{\PassOptionsToClass{openany}{book}}
\DeclareOption{openright}{\PassOptionsToClass{openright}{book}}
\DeclareOption{oneside}{\PassOptionsToClass{oneside}{book}}
\DeclareOption{twoside}{\PassOptionsToClass{twoside}{book}}
\DeclareOption{castellano}{\def\@idioma{\@castellano}}
\DeclareOption{galego}{\def\@idioma{\@galego}}
\DeclareOption{english}{\def\@idioma{\@english}}
\ProcessOptions\relax
\LoadClass[11pt,openright,oneside,a4paper,titlepage]{book}

% Cargamos los paquetes que necesitamos
\usepackage[utf8]{inputenc}
\RequirePackage[spanish,es-nodecimaldot,es-tabla]{babel}
\RequirePackage[a4paper,left=4cm,top=4cm,bottom=4cm,right=2.5cm]{geometry}
\RequirePackage{fancyhdr}
\RequirePackage[dvips]{graphicx}
\RequirePackage{float} % Necesario para el entorno de algoritmo

% Definimos el estilo de pagina
\pagestyle{fancy}

% Establecemos el interlineado a espacio y medio, aumentando la separacion entre
% parrafos
\newlength{\espacioParrafos}
\setlength{\espacioParrafos}{1.5em}
\renewcommand*{\baselinestretch}{1.5}
\parskip=\espacioParrafos

% Definimos las variables de los idiomas
\def\@castellano{castellano}
\def\@galego{galego}
\def\@english{english}

% Definimos las etiquetas a usar en función del lenguaje
\expandafter\ifx\@idioma\@galego
    \def\@universidad{UNIVERSIDADE DA CORU\~NA}
    \def\@facultad{FACULTADE DE INFORM\'ATICA}
    \def\@titulacionsuperior{PROXECTO DE FIN DE CARREIRA DE ENXE\~NER\'IA INFORM\'ATICA}
    \def\@titulaciongestion{PROXECTO DE FIN DE CARREIRA DE ENXE\~NER\'IA T\'ECNICA EN INFORM\'ATICA DE XESTI\'ON}
    \def\@titulacionsistemas{PROXECTO DE FIN DE CARREIRA DE ENXE\~NER\'IA T\'ECNICA EN INFORM\'ATICA DE SISTEMAS}
    \def\@titulaciondoctorado{TESIS DOCTORAL}
    \def\@tutortit{Titor}
    \def\@agradecimientos{Agradecementos}
    \def\@resumen{Resumo}
    \def\@palabrasclave{Verbas clave}
    \def\@indicecontenidos{Índice xeral}    
    \def\@tabla{Táboa}
    \def\@indicetablas{Índice de táboas}    
    \def\@indicealgoritmos{Índice de algoritmos}
    \def\@algoritmonombre{Algoritmo}      
    \def\@apendice{Apéndice}         
    \def\@direccion{\@facultad \\ Campus de Elvi\~na s/n \\ 15071 - A Coru\~na (Espa\~na)}
    \def\@fecha{\the\day~de \ifcase\month\or xaneiro\or febreiro\or
        marzo\or abril\or maio\or xunio\or xulio\or agosto\or
        setembro\or outubro\or novembro\or decembro\fi
        \ de\ifnum\year>1999\es@yearl\fi~\the\year}
    \def\@avisolegaltitle{Aviso legal:\\ Non est\'a permitida a reproducci\'on total ou parcial deste pro\-xec\-to,
                          nin o seu tratamento inform\'atico, nin a transmisi\'on de ningunha forma ou por calquera medio, xa
                          sexa electr\'onico, mec\'anico, por fotocopia, por rexistro u outros medios, sen o permiso previo
                          e por escrito do autor do mesmo.}           
\else
	\expandafter\ifx\@idioma\@english
	\def\@universidad{UNIVERSITY OF A CORU\~NA}
	\def\@facultad{FACULTY OF INFORMATICS}
	\def\@titulacionsuperior{PROXECTO DE FIN DE CARREIRA DE ENXE\~NER\'IA INFORM\'ATICA}
	\def\@titulaciongestion{PROXECTO DE FIN DE CARREIRA DE ENXE\~NER\'IA T\'ECNICA EN INFORM\'ATICA DE XESTI\'ON}
	\def\@titulacionsistemas{PROXECTO DE FIN DE CARREIRA DE ENXE\~NER\'IA T\'ECNICA EN INFORM\'ATICA DE SISTEMAS}
	\def\@titulaciondoctorado{PHD THESIS}
	\def\@tutortit{Tutor}
	\def\@agradecimientos{Acknowledgements}
	\def\@resumen{Summary}
	\def\@palabrasclave{Keywords}
	\def\@indicecontenidos{Table of contents}    
	\def\@tabla{Table}
	\def\@indicetablas{Table index}    
	\def\@indicealgoritmos{Algorithm index}
	\def\@algoritmonombre{Algorithm}      
	\def\@apendice{Appendix}         
	\def\@direccion{\@facultad \\ Campus de Elvi\~na s/n \\ 15071 - A Coru\~na (Spain)}
	\def\@fecha{\the\day~\ifcase\month\or January\or February\or
	    		March\or April\or May\or June\or July\or August\or
				September\or October\or November\or December\fi
				~\the\year}
	\def\@avisolegaltitle{Legal warning:\\ All total or partial reproduction, modification, and transmission by any means electronic, mechanic, fotocopy, record or other is strictly forbidden without previous written authorization from the author.}
	
\else
    \def\@universidad{UNIVERSIDAD DE A CORU\~NA}
    \def\@facultad{FACULTAD DE INFORM\'ATICA}
    \def\@titulacionsuperior{PROYECTO DE FIN DE CARRERA DE INGENIER\'IA INFORM\'ATICA}
    \def\@titulaciongestion{PROYECTO DE FIN DE CARRERA DE INGENIER\'IA T\'ECNICA EN INFORM\'ATICA DE GESTI\'ON}
    \def\@titulacionsistemas{PROYECTO DE FIN DE CARRERA DE INGENIER\'IA T\'ECNICA EN INFORM\'ATICA DE SISTEMAS}
    \def\@titulaciondoctorado{TESIS DOCTORAL}    
    \def\@tutortit{Tutor}
    \def\@agradecimientos{Agradecimientos}
    \def\@resumen{Resumen}
    \def\@palabrasclave{Palabras clave}
    \def\@indicecontenidos{Índice general}    
    \def\@tabla{Tabla}
    \def\@indicetablas{Índice de tablas}      
    \def\@indicealgoritmos{Índice de algoritmos}
    \def\@algoritmonombre{Algoritmo}      
    \def\@apendice{Apéndice}              
    \def\@direccion{\@facultad \\ Campus de Elvi\~na s/n \\ 15071 - A Coru\~na (Espa\~na)}
    \def\@fecha{\today}    
    \def\@avisolegaltitle{Aviso legal:\\ No est\'a permitida la reproducci\'on total o parcial de este proyecto,
                          ni su tratamiento inform\'atico, ni la transmisi\'on de ninguna forma o por cualquier medio, ya
                          sea electr\'onico, mec\'anico, por fotocopia, por registro u otros medios, sin el permiso previo
                          y por escrito del autor del mismo.}            
\fi
\fi

% Definimos las variables inicialmente vacias (excepto la fecha de lectura)
\def\@department{\empty}
\def\@degree{\empty}
\def\@title{\empty}
\def\@author{\empty}
\def\@tutor{\empty}
\def\@director{\empty}
\def\@directorsex{\empty}
\def\@directortit{\empty}
\def\@pfcdate{A Coru\~na, \@fecha}
\def\@dedication{\empty}

% Definimos el directorio que indica el lugar donde estarán las figuras usadas en la memoria.
% Por defecto se establece a vacio.
\def\@dFiguras{\empty}

\newcommand{\dirFiguras}[1]{%
    \def\@dFiguras{#1}
}

\newcommand{\figuras}{%
    \@dFiguras
}

% Definimos el directorio que indica el lugar donde estarán los diferentes capítulos de la memoria.
% Por defecto se establece a vacio.
\def\@dCapitulos{\empty}
% Definimos el comando para indicar el directorio en el que estarán los capitulos de la memoria
\newcommand{\dirCapitulos}[1]{%
    \def\@dCapitulos{#1}
}%newcommand
% Definimos el comando con el cual se incluyen los diferentes capítulos de la memoria en el fichero principal
\newcommand{\incluir}[1]{%
    \include{\@dCapitulos #1}
}%newcommand

% Definimos el comando para establecer el departamento. Por ejemplo
% \department{"Computacion}" estableceria "Departamento de Computacion"
\newcommand{\department}[1]{%
	\expandafter
	\ifx\@idioma\@english
		\def\@department{Department of #1}
	\else
    	\def\@department{Departamento de #1}
	\fi
}%newcommand

% Definimos el comando para indicar de que es el proyecto. "g" si es de Gestion,
% "s" si es de Sistemas, "i" si es de Ingenieria Informatica y "t" si es una tesis doctoral
\newcommand{\degree}[1]{%
    \ifx#1i\def\@degree{\@titulacionsuperior}\fi
    \ifx#1g\def\@degree{\@titulaciongestion}\fi
    \ifx#1s\def\@degree{\@titulacionsistemas}\fi
    \ifx#1t\def\@degree{\@titulaciondoctorado}\fi    
    \@ifundefined{hypersetup}{}{\hypersetup{pdfsubject={\@degree \ (\@universidad)}}}
}%newcommand

% Definimos el comando para establecer el tutor
\newcommand{\tutor}[2][m]{%
    \ifx#1f
        \def\@tutor{\textbf{\@tutortit a:} #2}
    \else
        \def\@tutor{\textbf{\@tutortit:} #2}
    \fi
}%newcommand

% Definimos el comando para establecer el director
\newlength{\lnmaxdirector}
\newlength{\lndirector}
\newlength{\lndirectortit}
\newcommand{\director}[2][m]{%
\expandafter\ifx\@director\empty
	\expandafter\ifx\@idioma\@english
		\def\@directortit{\textbf{Director}: }
	\else
	   \ifx#1f
	       \def\@directortit{\textbf{Directora}: }
	       \def\@directorsex{f}
	   \else
	       \def\@directortit{\textbf{Director}: }
	   \fi
	\fi
    \def\@director{#2}
    \settowidth{\lndirectortit}{\@directortit}
    \settowidth{\lnmaxdirector}{#2}    
\else    
    \expandafter
	\ifx\@idioma\@english
		\def\@directortit{\textbf{Directors}: }
	\else
		\ifx\@directorsex\empty
	        \def\@directortit{\textbf{Directores}: }
	    \else
	        \ifx#1m
	            \def\@directortit{\textbf{Directores}: }
	            \def\@directorsex{\empty}                    
	        \else
	            \def\@directortit{\textbf{Directoras}: }        
	        \fi    
	    \fi
	\fi
    \settowidth{\lndirectortit}{\@directortit}
    \settowidth{\lndirector}{#2}    
    \ifdim\lnmaxdirector<\lndirector
        \settowidth{\lnmaxdirector}{#2}
    \fi    
    \parskip=-1em % Para que no deje una línea en blanco entre los nombres de los directores
    \addto\@director{\vskip\parsep\hskip\lndirectortit\hskip-0.5em #2}
    % El xdef daba problema con los acentos asi que se usa el \addto del paquete babel
    %\xdef\@director{\@director\vskip\parsep\hskip\lndirectortit\hskip-0.5em #2}    
\fi
}%newcommand

% Definimos el comando para establecer la fecha de lectura del proyecto
\newcommand{\pfcdate}[1]{%
    \def\@pfcdate{A Coru\~na, #1}
}%newcommand

% Definimos el comando para establecer una dedicatoria
\newcommand{\dedication}[1]{%
    \def\@dedication{#1}
}%newcommand

% Redefinimos el comando para establecer el autor
\renewcommand{\author}[2][m]{%
	\expandafter
	\ifx\@idioma\@english
		\def\@author{\textbf{Author:} #2}
	\else
    	\ifx#1f
        	\def\@author{\textbf{Autora:} #2}
    	\else
        	\def\@author{\textbf{Autor:} #2}
    \fi\fi
    \@ifundefined{hypersetup}{}{\hypersetup{pdfauthor={#2}}}
}%renewcommand

% Redefinimos el comando para establecer el titulo del proyecto
\renewcommand{\title}[1]{%
    \def\@title{#1}
    \@ifundefined{hypersetup}{}{\hypersetup{pdftitle={#1}}}
}%renewcommand

% Redefinimos parte del comando de las notas a pie de página para cambiar el interlineado
\long\def\@footnotetext#1{\renewcommand*{\baselinestretch}{1}\insert\footins{%
    \reset@font\footnotesize
    \interlinepenalty\interfootnotelinepenalty
    \splittopskip\footnotesep
    \splitmaxdepth \dp\strutbox \floatingpenalty \@MM
    \hsize\columnwidth \@parboxrestore
    \protected@edef\@currentlabel{%
       \csname p@footnote\endcsname\@thefnmark
    }%
    \color@begingroup
      \@makefntext{%
        \rule\z@\footnotesep\ignorespaces#1\@finalstrut\strutbox}%
    \color@endgroup}\renewcommand*{\baselinestretch}{1.5}}%
   
% Redefinimos el frontmatter que viene en la clase book
\renewcommand\frontmatter{%
    % Parte standard del mainmatter
    \cleardoublepage
    \@mainmatterfalse
    \pagenumbering{roman}
    % Parte añadida para las cabeceras
    \renewcommand{\headrulewidth}{0pt}
    \fancyhf[H]{}
    \parskip=\espacioParrafos % Se establece de nuevo el espacio entre párrafos porque se ha cambiado en el comando director   
}%renewcommand

% Redefinimos el mainmatter que viene en la clase book
\renewcommand{\mainmatter}{
    % Parte standard del mainmatter
    \cleardoublepage
    \@mainmattertrue
    \pagenumbering{arabic}
    % Parte añadida para las cabeceras
    \renewcommand{\headrulewidth}{0.4pt}
}%renewcommand

% Redefinimos el backmatter que viene en la clase book
\renewcommand{\backmatter}{%
  \if@openright
    \cleardoublepage
  \else
    \clearpage
  \fi
  \@mainmatterfalse
  \pagestyle{plain} % A partir del backmatter el estilo es plano
}

% Redefinimos las cabeceras para que reflejen la seccion
\renewcommand{\sectionmark}[1]{%
    \fancyhf[HOR]{\thesection\quad #1}
    \fancyhf[HOL]{}
}%renewcommand

% Redefinimos las cabeceras para que reflejen el capitulo
\renewcommand{\chaptermark}[1]{%
    \fancyhf[HEL]{\chaptername \ \thechapter.\ #1}
    \fancyhf[HER]{}
% Mientras no haya nueva sección se ponen las marcas en blanco
    \fancyhf[HOR]{}
    \fancyhf[HOL]{}
}%renewcommand

% Redefinimos el comando para crear la pagina de titulo
\renewcommand{\maketitle}{%
    \renewcommand{\headrulewidth}{0pt}

	\pagenumbering{alph}

    {\pagestyle{empty}
    % Colocamos los distintos componentes de la pagina principal
    \begin{titlepage}
    \vspace*{-2.5cm}
    \centering\includegraphics[width=4.5cm]{\figuras/logoudc.eps} \\
    \usefont{T1}{phv}{m}{n}\centering\large \@universidad \\
    \vspace{1cm}
    \usefont{T1}{phv}{b}{n}\centering\LARGE \@facultad \\
    \vspace{0.5cm}
    \usefont{T1}{phv}{m}{sl}\centering\large \@department \\
    \vspace{0.5cm}
    \usefont{T1}{phv}{m}{n}\centering\large \@degree \\
    \vspace{3.5cm}
    \renewcommand*{\baselinestretch}{2}
    \usefont{T1}{phv}{b}{sl}\centering\huge \@title
    \normalsize

    % Calculamos el tamaño que debe tener la minipage que contenga el autor,
    % directores, tutor y fecha del proyecto
    \newlength{\lnminipage}
    \newlength{\lnline}
    \settowidth{\lnminipage}{\@author}
    \settowidth{\lnline}{\@tutor}
    \ifdim\lnminipage<\lnline
        \settowidth{\lnminipage}{\@tutor}
    \fi
    \settowidth{\lnline}{\@directortit}
    \addtolength{\lnmaxdirector}{\lnline}
    \settowidth{\lnline}{0.5em}
    \addtolength{\lnmaxdirector}{\lnline} % Se añade 0.5em porque el título de director está en negrita
    \ifdim\lnminipage<\lnmaxdirector
        \setlength{\lnminipage}{\lnmaxdirector}
    \fi
    \settowidth{\lnline}{\@pfcdate}
    \ifdim\lnminipage<\lnline
        \settowidth{\lnminipage}{\@pfcdate}
    \fi

    % Colocamos la minipage abajo y a la derecha de la pagina del titulo
    \vfill
    \enlargethispage{2cm}
    \begin{flushright}
    \begin{minipage}{\lnminipage}
    \usefont{T1}{phv}{m}{n}
    \@author \\[-1em]
    % Si no hay tutor lo saltamos
    \expandafter\ifx\@tutor\empty\empty\else\@tutor\\[-1em]\fi
    \@directortit
    \@director \\
    \@pfcdate
    \end{minipage}
    \end{flushright}
    \normalfont
    \end{titlepage}

    % En una nueva página se coloca el lugar y la dirección de la impresión
    % así como el aviso legal de reproducción de la obra
    \cleardoublepage
    \enlargethispage{2cm}
    \begin{flushright} %
    \begin{scriptsize}
    \usefont{T1}{phv}{m}{n}
    \begin{minipage}{0.35\textwidth}
    \vspace{54em}
    \@fecha\\
    \@universidad \\[2em]
    \@direccion
    \end{minipage}\\[6em]
    \fbox{
        \begin{minipage}[b]{0.6\textwidth}
        \begin{description}
        \item[] \@avisolegaltitle
        \end{description}
        \end{minipage}
    }
    \end{scriptsize} %
    \end{flushright} %
    \cleardoublepage

    % Si hay dedicatoria la colocamos en una pagina aparte, a la derecha y centrada
    % verticalmente
    \expandafter\ifx\@dedication\empty\empty
    \else
        \cleardoublepage %
        \thispagestyle{empty} %
        \begin{flushright} %
        \quad
        \vfil
        \begin{large}\textit{\@dedication}\end{large} %
        \vfil
        \quad
        \end{flushright} %
    \fi
    \cleardoublepage
    } % del \pagestyle
}%renewcommand
  
% Establecemos un entorno para los agredecimientos. Opcionalmente podemos
% indicarle una firma
\newenvironment{agradecimientos}[1][\empty]
{%
%    \parindent=0em
    \newcommand{\Autor}{#1}
    \chapter*{\@agradecimientos}
}
{%
    \par\vspace{\baselineskip}
    \begin{flushright}
    \textsl{\Autor}
    \end{flushright}
}%newenvironment

% Establecemos un entorno para el resumen del proyecto. Dentro de este entorno
% podemos indicar las palabras clave del proyecto
\newenvironment{resumen}
{%
    \def\@keywords{\empty}
%    \parindent=0em
    \newcommand{\keywords}[1]{%
        \@ifundefined{hypersetup}{}{\hypersetup{pdfkeywords={##1}}}
        \def\@keywords{\par\vspace{\baselineskip} \begin{large}\scshape\textbf{\@palabrasclave:} \textit{##1}\end{large}}
    }%newcommand
    \chapter*{\@resumen}
}
{%
\@keywords
}

% Definimos el comando para el formato de los índices (contenidos, figuras y tablas)
\newcommand{\indices}[2]{%
    % Seleccionamos el lenguaje y los títulos de indices en español
    %\selectlanguage{spanish}
    \def\tablename{\@tabla}
    \def\listtablename{\@indicetablas}
    \def\contentsname{\@indicecontenidos}
    % Seleccionamos el título del indice de algoritmos
    \def\@listalgoritmonombre{\@indicealgoritmos}
    % Profundidad a mostrar en los índices
    \setcounter{tocdepth}{3}
    % Profundidad a numerar en los índices
    \setcounter{secnumdepth}{3}        
    % Separación en los índices
    \parskip = #1                            
    \tableofcontents      
    % Añade el resto de índices indicados (segundo parámetro del comando)
    #2
    \parskip = \espacioParrafos
}%newcommand

% Definimos el entorno para incluir algoritmos
\floatstyle{ruled}
\newfloat{algoritmo}{htbp}{loa}
\floatname{algoritmo}{\@algoritmonombre}
\newcommand{\theHalgoritmo}{}
\newcommand{\listofalgoritmos}{%
    \listof{algoritmo}{\@listalgoritmonombre}
}%newcommand
\@namedef{thealgoritmo}{\thechapter.3\arabic{algoritmo}.}

% Redefinimos el appendix que viene en la clase book
\renewcommand\appendix{
  \if@openright\cleardoublepage\else\clearpage\fi
  \par
  \setcounter{chapter}{0}%
  \setcounter{section}{0}%
  \gdef\@chapapp{\@apendice}%
  \gdef\thechapter{\@Roman\c@chapter}
  \renewcommand\chaptername{\@apendice}  
}%renewcommand

% Definimos el comando \@removefromreset que invierte el efecto de \@addtorest.
% En este caso se aplica para que la clase book no resetee la numeración de los pie de página (footnote)
% en cada capítulo.
\def\@removefromreset#1#2{{%
  \expandafter\let\csname c@#1\endcsname\@removefromreset
  \def\@elt##1{%
    \expandafter\ifx\csname c@##1\endcsname\@removefromreset
    \else
      \noexpand\@elt{##1}%
    \fi}%
  \expandafter\xdef\csname cl@#2\endcsname{%
    \csname cl@#2\endcsname}}}
\@removefromreset{footnote}{chapter}


\endinput
