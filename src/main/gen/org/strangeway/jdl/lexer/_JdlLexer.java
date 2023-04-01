/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package org.strangeway.jdl.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.strangeway.jdl.psi.JdlTokenTypes.*;

import it.unimi.dsi.fastutil.ints.IntArrayList;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>jdl.flex</tt>
 */
public class _JdlLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int BRACES_BODY = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [11, 6, 4]
   * Total runtime size is 14112 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[(ZZ_CMAP_Z[ch>>10]<<6)|((ch>>4)&0x3f)]<<4)|(ch&0xf)];
  }

  /* The ZZ_CMAP_Z table has 1088 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\2\11\1\12\1\13\6\14\1\15\23\14\1\16"+
    "\1\14\1\17\1\20\12\14\1\21\10\11\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1"+
    "\32\1\33\1\34\1\35\2\11\1\14\1\36\3\11\1\37\10\11\1\40\1\41\5\14\1\42\1\43"+
    "\11\11\1\44\2\11\1\45\5\11\1\46\4\11\1\47\1\50\4\11\51\14\1\51\3\14\1\52\1"+
    "\53\4\14\1\54\12\11\1\55\u0381\11");

  /* The ZZ_CMAP_Y table has 2944 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\1\1\11\1\12\1\13\1\14\1\13\1\14\34"+
    "\13\1\15\1\16\1\17\10\1\1\20\1\21\1\13\1\22\4\13\1\23\10\13\1\24\12\13\1\25"+
    "\1\13\1\26\1\25\1\13\1\27\4\1\1\13\1\30\1\31\2\1\2\13\1\30\1\1\1\32\1\25\5"+
    "\13\1\33\1\34\1\35\1\1\1\36\1\13\1\1\1\37\5\13\1\40\1\41\1\42\1\13\1\30\1"+
    "\43\1\13\1\44\1\45\1\1\1\13\1\46\4\1\1\13\1\47\4\1\1\50\2\13\1\51\1\1\1\52"+
    "\1\16\1\25\1\53\1\54\1\55\1\56\1\57\1\60\2\16\1\61\1\54\1\55\1\62\1\1\1\63"+
    "\1\1\1\64\1\65\1\22\1\55\1\66\1\1\1\67\1\16\1\70\1\71\1\54\1\55\1\66\1\1\1"+
    "\60\1\16\1\41\1\72\1\73\1\74\1\75\1\1\1\67\2\1\1\76\1\36\1\55\1\51\1\1\1\77"+
    "\1\16\1\1\1\100\1\36\1\55\1\101\1\1\1\57\1\16\1\102\1\76\1\36\1\13\1\103\1"+
    "\57\1\104\1\16\1\42\1\105\1\106\1\13\1\107\1\110\3\1\1\25\2\13\1\111\1\110"+
    "\3\1\1\112\1\113\1\114\1\115\1\116\1\117\2\1\1\67\3\1\1\120\1\13\1\121\1\1"+
    "\1\122\7\1\2\13\1\30\1\123\1\1\1\124\1\125\1\126\1\127\1\1\2\13\1\130\2\13"+
    "\1\131\24\13\1\132\1\133\2\13\1\132\2\13\1\134\1\135\1\14\3\13\1\135\3\13"+
    "\1\30\2\1\1\13\1\1\5\13\1\136\1\25\45\13\1\137\1\13\1\25\1\30\4\13\1\30\1"+
    "\140\1\141\1\16\1\13\1\16\1\13\1\16\1\141\1\67\3\13\1\142\1\1\1\143\4\1\5"+
    "\13\1\27\1\144\1\13\1\145\4\13\1\40\1\13\1\146\3\1\1\13\1\147\1\150\2\13\1"+
    "\151\1\13\1\75\3\1\1\13\1\110\3\13\1\150\4\1\1\152\5\1\1\105\2\13\1\142\1"+
    "\153\3\1\1\154\1\13\1\155\1\42\2\13\1\40\1\1\2\13\1\142\1\1\1\37\1\42\1\13"+
    "\1\147\1\46\5\1\1\156\1\157\14\13\4\1\21\13\1\136\2\13\1\136\1\160\1\13\1"+
    "\147\3\13\1\161\1\162\1\163\1\121\1\162\2\1\1\164\4\1\1\165\1\1\1\121\6\1"+
    "\1\166\1\167\1\170\1\171\1\172\3\1\1\173\147\1\2\13\1\146\2\13\1\146\10\13"+
    "\1\174\1\175\2\13\1\130\3\13\1\176\1\1\1\13\1\110\4\177\4\1\1\123\35\1\1\200"+
    "\2\1\1\201\1\25\4\13\1\202\1\25\4\13\1\131\1\105\1\13\1\147\1\25\4\13\1\146"+
    "\1\1\1\13\1\30\3\1\1\13\40\1\133\13\1\40\4\1\135\13\1\40\2\1\10\13\1\121\4"+
    "\1\2\13\1\147\20\13\1\121\1\13\1\203\1\1\2\13\1\146\1\123\1\13\1\147\4\13"+
    "\1\40\2\1\1\204\1\205\5\13\1\206\1\13\1\146\1\27\3\1\1\204\1\207\1\13\1\31"+
    "\1\1\3\13\1\142\1\205\2\13\1\142\3\1\1\210\1\42\1\13\1\40\1\13\1\110\1\1\1"+
    "\13\1\121\1\50\2\13\1\31\1\123\1\1\1\211\1\212\2\13\1\46\1\1\1\213\1\1\1\13"+
    "\1\214\3\13\1\215\1\216\1\217\1\30\1\64\1\220\1\221\1\177\2\13\1\131\1\40"+
    "\7\13\1\31\1\1\72\13\1\142\1\13\1\222\2\13\1\151\20\1\26\13\1\147\6\13\1\75"+
    "\2\1\1\110\1\223\1\55\1\224\1\225\6\13\1\16\1\1\1\154\25\13\1\147\1\1\4\13"+
    "\1\205\2\13\1\27\2\1\1\151\7\1\1\211\7\13\1\121\2\1\1\25\1\30\1\25\1\30\1"+
    "\226\4\13\1\146\1\227\1\230\2\1\1\231\1\13\1\14\1\232\2\147\2\1\7\13\1\30"+
    "\30\1\1\13\1\121\3\13\1\67\2\1\2\13\1\1\1\13\1\233\2\13\1\40\1\13\1\147\2"+
    "\13\1\234\3\1\11\13\1\147\1\1\2\13\1\234\1\13\1\151\2\13\1\27\3\13\1\142\11"+
    "\1\23\13\1\110\1\13\1\40\1\27\11\1\1\235\2\13\1\236\1\13\1\40\1\13\1\110\1"+
    "\13\1\146\4\1\1\13\1\237\1\13\1\40\1\13\1\75\4\1\3\13\1\240\4\1\1\67\1\241"+
    "\1\13\1\142\2\1\1\13\1\121\1\13\1\121\2\1\1\120\1\13\1\150\1\1\3\13\1\40\1"+
    "\13\1\40\1\13\1\31\1\13\1\16\6\1\4\13\1\46\3\1\3\13\1\31\3\13\1\31\60\1\1"+
    "\154\2\13\1\27\4\1\1\154\2\13\2\1\1\13\1\46\1\1\1\154\1\13\1\110\2\1\2\13"+
    "\1\242\1\154\2\13\1\31\1\243\1\244\2\1\1\13\1\22\1\151\5\1\1\245\1\246\1\46"+
    "\2\13\1\146\2\1\1\71\1\54\1\55\1\66\1\1\1\247\1\16\11\1\3\13\1\150\1\250\3"+
    "\1\3\13\1\1\1\251\13\1\2\13\1\146\2\1\1\252\2\1\3\13\1\1\1\253\3\1\2\13\1"+
    "\30\5\1\1\13\1\75\30\1\4\13\1\1\1\123\34\1\3\13\1\46\20\1\1\55\1\13\1\146"+
    "\1\1\1\67\2\1\1\205\1\13\67\1\71\13\1\75\16\1\14\13\1\142\53\1\2\13\1\146"+
    "\75\1\44\13\1\110\33\1\43\13\1\46\1\13\1\146\7\1\1\13\1\147\1\1\3\13\1\1\1"+
    "\142\1\1\1\154\1\254\1\13\67\1\4\13\1\150\1\67\3\1\1\154\4\1\1\67\1\1\76\13"+
    "\1\121\1\1\57\13\1\31\20\1\1\16\77\1\6\13\1\30\1\121\1\46\1\75\66\1\5\13\1"+
    "\211\3\13\1\141\1\255\1\256\1\257\3\13\1\260\1\261\1\13\1\262\1\263\1\36\24"+
    "\13\1\264\1\13\1\36\1\131\1\13\1\131\1\13\1\211\1\13\1\211\1\146\1\13\1\146"+
    "\1\13\1\55\1\13\1\55\1\13\1\213\3\1\14\13\1\150\3\1\4\13\1\142\113\1\1\257"+
    "\1\13\1\265\1\266\1\267\1\270\1\271\1\272\1\273\1\151\1\274\1\151\24\1\55"+
    "\13\1\110\2\1\103\13\1\150\15\13\1\147\150\13\1\16\25\1\41\13\1\147\36\1");

  /* The ZZ_CMAP_A table has 3024 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\20\1\15\2\16\1\4\22\0\1\20\1\0\1\5\5\0\1\51\1\52\1\17\1\14\1\45\1"+
    "\2\1\12\1\6\1\10\11\11\1\46\2\0\1\56\2\0\1\55\4\7\1\13\25\7\1\47\1\3\1\50"+
    "\1\0\1\1\1\0\1\40\1\7\1\32\1\44\1\21\1\36\1\42\1\30\1\26\2\7\1\41\1\24\1\22"+
    "\1\34\1\33\1\7\1\37\1\35\1\27\1\23\1\7\1\25\1\31\1\43\1\7\1\53\1\0\1\54\7"+
    "\0\1\16\24\0\1\1\12\0\1\1\4\0\1\1\5\0\27\1\1\0\12\1\4\0\14\1\16\0\5\1\7\0"+
    "\1\1\1\0\1\1\1\0\5\1\1\0\2\1\2\0\4\1\1\0\1\1\6\0\1\1\1\0\3\1\1\0\1\1\1\0\4"+
    "\1\1\0\23\1\1\0\13\1\10\0\6\1\1\0\26\1\2\0\1\1\6\0\10\1\10\0\13\1\5\0\3\1"+
    "\33\0\6\1\1\0\1\1\17\0\2\1\7\0\2\1\12\0\3\1\2\0\2\1\1\0\16\1\15\0\11\1\13"+
    "\0\1\1\30\0\6\1\4\0\2\1\4\0\1\1\5\0\6\1\4\0\1\1\11\0\1\1\3\0\1\1\7\0\11\1"+
    "\7\0\5\1\1\0\10\1\6\0\26\1\3\0\1\1\2\0\1\1\7\0\11\1\4\0\10\1\2\0\2\1\2\0\26"+
    "\1\1\0\7\1\1\0\1\1\3\0\4\1\3\0\1\1\20\0\1\1\15\0\2\1\1\0\1\1\5\0\6\1\4\0\2"+
    "\1\1\0\2\1\1\0\2\1\1\0\2\1\17\0\4\1\1\0\1\1\3\0\3\1\20\0\11\1\1\0\2\1\1\0"+
    "\2\1\1\0\5\1\3\0\1\1\2\0\1\1\30\0\1\1\13\0\10\1\2\0\1\1\3\0\1\1\1\0\6\1\3"+
    "\0\3\1\1\0\4\1\3\0\2\1\1\0\1\1\1\0\2\1\3\0\2\1\3\0\3\1\3\0\14\1\13\0\10\1"+
    "\1\0\2\1\10\0\3\1\5\0\1\1\4\0\10\1\1\0\6\1\1\0\5\1\3\0\1\1\3\0\2\1\15\0\13"+
    "\1\2\0\1\1\6\0\3\1\10\0\1\1\5\0\22\1\3\0\10\1\1\0\11\1\1\0\1\1\2\0\7\1\11"+
    "\0\1\1\1\0\2\1\15\0\2\1\1\0\1\1\2\0\2\1\1\0\1\1\2\0\1\1\6\0\4\1\1\0\7\1\1"+
    "\0\3\1\1\0\1\1\1\0\1\1\2\0\2\1\1\0\4\1\1\0\2\1\11\0\1\1\2\0\5\1\1\0\1\1\25"+
    "\0\14\1\1\0\24\1\13\0\5\1\22\0\7\1\4\0\4\1\3\0\1\1\3\0\2\1\7\0\3\1\4\0\15"+
    "\1\14\0\1\1\1\0\6\1\1\0\1\1\5\0\1\1\2\0\13\1\1\0\15\1\1\0\4\1\2\0\7\1\1\0"+
    "\1\1\1\0\4\1\2\0\1\1\1\0\4\1\2\0\7\1\1\0\1\1\1\0\4\1\2\0\16\1\2\0\6\1\2\0"+
    "\15\1\2\0\1\1\1\0\10\1\7\0\15\1\1\0\6\1\23\0\1\1\4\0\1\1\3\0\5\1\2\0\22\1"+
    "\1\0\1\1\5\0\17\1\1\0\16\1\2\0\5\1\13\0\14\1\13\0\1\1\15\0\7\1\7\0\16\1\15"+
    "\0\2\1\11\0\4\1\1\0\4\1\3\0\2\1\11\0\10\1\1\0\1\1\1\0\1\1\1\0\1\1\1\0\6\1"+
    "\1\0\7\1\1\0\1\1\3\0\3\1\1\0\7\1\3\0\4\1\2\0\6\1\14\0\2\16\7\0\1\1\15\0\1"+
    "\1\2\0\1\1\4\0\1\1\2\0\12\1\1\0\1\1\3\0\5\1\6\0\1\1\1\0\1\1\1\0\1\1\1\0\4"+
    "\1\1\0\13\1\2\0\4\1\5\0\5\1\4\0\1\1\4\0\2\1\13\0\5\1\6\0\4\1\3\0\2\1\14\0"+
    "\10\1\7\0\10\1\1\0\7\1\6\0\2\1\12\0\5\1\5\0\2\1\3\0\7\1\6\0\3\1\12\0\2\1\13"+
    "\0\11\1\2\0\27\1\2\0\7\1\1\0\3\1\1\0\4\1\1\0\4\1\2\0\6\1\3\0\1\1\1\0\1\1\2"+
    "\0\5\1\1\0\12\1\12\0\5\1\1\0\3\1\1\0\10\1\4\0\7\1\3\0\1\1\3\0\2\1\1\0\1\1"+
    "\3\0\2\1\2\0\5\1\2\0\1\1\1\0\1\1\30\0\3\1\3\0\6\1\2\0\6\1\2\0\6\1\11\0\7\1"+
    "\4\0\5\1\3\0\5\1\5\0\1\1\1\0\10\1\1\0\5\1\1\0\1\1\1\0\2\1\1\0\2\1\1\0\12\1"+
    "\6\0\12\1\2\0\6\1\2\0\6\1\2\0\6\1\2\0\3\1\3\0\14\1\1\0\16\1\1\0\2\1\1\0\2"+
    "\1\1\0\10\1\6\0\4\1\4\0\16\1\2\0\1\1\1\0\14\1\1\0\2\1\3\0\1\1\2\0\4\1\1\0"+
    "\2\1\12\0\10\1\6\0\6\1\1\0\3\1\1\0\12\1\3\0\1\1\12\0\4\1\25\0\1\1\1\0\1\1"+
    "\3\0\7\1\1\0\1\1\1\0\4\1\1\0\17\1\1\0\2\1\14\0\3\1\7\0\4\1\11\0\2\1\1\0\1"+
    "\1\20\0\4\1\10\0\1\1\13\0\10\1\5\0\3\1\2\0\1\1\2\0\2\1\2\0\4\1\1\0\14\1\1"+
    "\0\1\1\1\0\7\1\1\0\21\1\1\0\4\1\2\0\10\1\1\0\7\1\1\0\14\1\1\0\4\1\1\0\5\1"+
    "\1\0\1\1\3\0\14\1\2\0\10\1\1\0\2\1\1\0\1\1\2\0\1\1\1\0\12\1\1\0\4\1\1\0\1"+
    "\1\1\0\1\1\6\0\1\1\4\0\1\1\1\0\1\1\1\0\1\1\1\0\3\1\1\0\2\1\1\0\1\1\2\0\1\1"+
    "\1\0\1\1\1\0\1\1\1\0\1\1\1\0\1\1\1\0\2\1\1\0\1\1\2\0\4\1\1\0\7\1\1\0\4\1\1"+
    "\0\4\1\1\0\1\1\1\0\12\1\1\0\5\1\1\0\3\1\1\0\5\1\1\0\5\1");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\4\1\5\2\6\1\1"+
    "\1\3\1\7\1\10\11\2\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\3\2\2\4"+
    "\2\5\1\23\1\24\1\0\2\25\4\2\1\26\10\2"+
    "\1\5\2\24\1\5\1\25\3\2\1\27\3\2\1\30"+
    "\4\2\1\0\1\24\1\31\2\2\1\32\1\33\5\2"+
    "\1\24\3\2\1\34\3\2\1\35\1\36\1\37\16\2"+
    "\1\40\1\2\1\41\1\42";

  private static int [] zzUnpackAction() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\353\0\u011a\0\u0149"+
    "\0\u0178\0\u01a7\0\136\0\136\0\u01d6\0\u0205\0\u0234\0\u0263"+
    "\0\u0292\0\u02c1\0\u02f0\0\u031f\0\u034e\0\u037d\0\136\0\136"+
    "\0\136\0\136\0\136\0\136\0\136\0\136\0\136\0\136"+
    "\0\u03ac\0\u03db\0\u040a\0\u0439\0\136\0\u0468\0\u0497\0\u04c6"+
    "\0\u04f5\0\u0149\0\u0524\0\u0553\0\u0582\0\u05b1\0\u05e0\0\u060f"+
    "\0\215\0\u063e\0\u066d\0\u069c\0\u06cb\0\u06fa\0\u0729\0\u0758"+
    "\0\u0787\0\u07b6\0\u07e5\0\u0814\0\u0843\0\u0872\0\u08a1\0\u08d0"+
    "\0\u08ff\0\215\0\u092e\0\u095d\0\u098c\0\215\0\u09bb\0\u09ea"+
    "\0\u0a19\0\u0a48\0\u0a77\0\u07b6\0\215\0\u0aa6\0\u0ad5\0\215"+
    "\0\215\0\u0b04\0\u0b33\0\u0b62\0\u0b91\0\u0bc0\0\136\0\u0bef"+
    "\0\u0c1e\0\u0c4d\0\215\0\u0c7c\0\u0cab\0\u0cda\0\215\0\215"+
    "\0\215\0\u0d09\0\u0d38\0\u0d67\0\u0d96\0\u0dc5\0\u0df4\0\u0e23"+
    "\0\u0e52\0\u0e81\0\u0eb0\0\u0edf\0\u0f0e\0\u0f3d\0\u0f6c\0\215"+
    "\0\u0f9b\0\215\0\215";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\2\3\1\5\1\6\1\7\1\4\1\10"+
    "\1\11\1\12\1\4\1\3\1\13\1\3\1\14\1\15"+
    "\1\16\1\4\1\17\1\4\1\20\1\4\1\21\2\4"+
    "\1\22\3\4\1\23\1\24\1\25\3\4\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\1\3\1\4\2\3\1\5\1\6\1\7\1\4"+
    "\1\10\1\11\1\12\1\4\1\3\1\13\1\3\1\14"+
    "\1\15\1\41\1\4\1\17\1\4\1\20\1\4\1\42"+
    "\6\4\1\43\6\4\1\27\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\60\0\2\4\4\0"+
    "\5\4\5\0\24\4\27\0\1\13\41\0\3\6\1\44"+
    "\1\0\1\45\7\6\1\0\41\6\3\46\1\47\2\46"+
    "\1\50\10\46\1\51\37\46\10\0\2\52\1\53\1\54"+
    "\5\0\1\54\45\0\2\11\1\53\1\54\5\0\1\54"+
    "\45\0\2\53\65\0\1\15\37\0\2\4\4\0\5\4"+
    "\5\0\1\4\1\55\6\4\1\56\13\4\13\0\2\4"+
    "\4\0\5\4\5\0\14\4\1\57\7\4\13\0\2\4"+
    "\4\0\5\4\5\0\5\4\1\60\16\4\13\0\2\4"+
    "\4\0\5\4\5\0\13\4\1\61\2\4\1\62\5\4"+
    "\13\0\2\4\4\0\5\4\5\0\13\4\1\63\10\4"+
    "\13\0\2\4\4\0\5\4\5\0\13\4\1\64\3\4"+
    "\1\65\4\4\13\0\2\4\4\0\5\4\5\0\1\66"+
    "\23\4\13\0\2\4\4\0\5\4\5\0\12\4\1\67"+
    "\11\4\13\0\2\4\4\0\5\4\5\0\1\70\23\4"+
    "\13\0\2\4\4\0\5\4\5\0\1\4\1\71\6\4"+
    "\1\56\13\4\13\0\2\4\4\0\5\4\5\0\13\4"+
    "\1\61\10\4\13\0\2\4\4\0\5\4\5\0\13\4"+
    "\1\64\10\4\12\0\4\6\1\0\10\6\1\0\41\6"+
    "\3\46\1\47\2\46\1\72\53\46\1\47\53\46\4\50"+
    "\1\0\10\50\2\0\40\50\3\51\1\73\2\51\1\74"+
    "\10\51\1\75\37\51\10\0\2\53\1\0\1\54\5\0"+
    "\1\54\37\0\1\76\5\0\2\76\2\0\1\76\43\0"+
    "\2\4\4\0\5\4\5\0\2\4\1\77\3\4\1\100"+
    "\15\4\13\0\2\4\4\0\5\4\5\0\11\4\1\101"+
    "\12\4\13\0\2\4\4\0\5\4\5\0\1\102\23\4"+
    "\13\0\2\4\4\0\5\4\5\0\6\4\1\103\15\4"+
    "\13\0\2\4\4\0\5\4\5\0\2\4\1\104\21\4"+
    "\13\0\2\4\4\0\5\4\5\0\1\4\1\105\22\4"+
    "\13\0\2\4\4\0\5\4\5\0\16\4\1\106\5\4"+
    "\13\0\2\4\4\0\5\4\5\0\20\4\1\107\3\4"+
    "\13\0\2\4\4\0\5\4\5\0\20\4\1\110\3\4"+
    "\13\0\2\4\4\0\5\4\5\0\12\4\1\111\11\4"+
    "\13\0\2\4\4\0\5\4\5\0\12\4\1\112\11\4"+
    "\13\0\2\4\4\0\5\4\5\0\2\4\1\77\21\4"+
    "\21\0\1\72\3\0\1\72\5\0\24\72\12\0\3\51"+
    "\1\73\13\51\1\75\37\51\17\74\1\113\37\74\3\51"+
    "\1\73\2\51\1\114\10\51\1\75\37\51\10\0\2\76"+
    "\46\0\2\4\4\0\5\4\5\0\3\4\1\115\20\4"+
    "\13\0\2\4\4\0\5\4\5\0\5\4\1\116\16\4"+
    "\13\0\2\4\4\0\5\4\5\0\1\117\23\4\13\0"+
    "\2\4\4\0\5\4\5\0\7\4\1\120\14\4\13\0"+
    "\2\4\4\0\5\4\5\0\1\121\23\4\13\0\2\4"+
    "\4\0\5\4\5\0\15\4\1\122\6\4\13\0\2\4"+
    "\4\0\5\4\5\0\14\4\1\123\7\4\13\0\2\4"+
    "\4\0\5\4\5\0\17\4\1\124\4\4\13\0\2\4"+
    "\4\0\5\4\5\0\20\4\1\125\3\4\13\0\2\4"+
    "\4\0\5\4\5\0\20\4\1\126\3\4\12\0\6\74"+
    "\1\127\10\74\1\113\37\74\1\0\2\4\4\0\5\4"+
    "\5\0\6\4\1\130\15\4\13\0\2\4\4\0\5\4"+
    "\5\0\12\4\1\131\11\4\13\0\2\4\4\0\5\4"+
    "\5\0\5\4\1\132\16\4\13\0\2\4\4\0\5\4"+
    "\5\0\1\133\23\4\13\0\2\4\4\0\5\4\5\0"+
    "\6\4\1\134\15\4\13\0\2\4\4\0\5\4\5\0"+
    "\5\4\1\135\16\4\13\0\2\4\4\0\5\4\5\0"+
    "\13\4\1\136\10\4\13\0\2\4\4\0\5\4\5\0"+
    "\22\4\1\137\1\4\13\0\2\4\4\0\5\4\5\0"+
    "\6\4\1\140\15\4\13\0\2\4\4\0\5\4\5\0"+
    "\21\4\1\141\2\4\13\0\2\4\4\0\5\4\5\0"+
    "\5\4\1\142\16\4\13\0\2\4\4\0\5\4\5\0"+
    "\11\4\1\143\12\4\13\0\2\4\4\0\5\4\5\0"+
    "\22\4\1\144\1\4\13\0\2\4\4\0\5\4\5\0"+
    "\13\4\1\145\10\4\13\0\2\4\4\0\5\4\5\0"+
    "\17\4\1\146\4\4\13\0\2\4\4\0\5\4\5\0"+
    "\3\4\1\147\20\4\13\0\2\4\4\0\5\4\5\0"+
    "\1\4\1\150\22\4\13\0\2\4\4\0\5\4\5\0"+
    "\6\4\1\151\15\4\13\0\2\4\4\0\5\4\5\0"+
    "\1\152\23\4\13\0\2\4\4\0\5\4\5\0\14\4"+
    "\1\153\7\4\13\0\2\4\4\0\5\4\5\0\5\4"+
    "\1\154\16\4\13\0\2\4\4\0\5\4\5\0\1\4"+
    "\1\155\22\4\13\0\2\4\4\0\5\4\5\0\7\4"+
    "\1\156\14\4\13\0\2\4\4\0\5\4\5\0\13\4"+
    "\1\157\10\4\13\0\2\4\4\0\5\4\5\0\6\4"+
    "\1\160\15\4\13\0\2\4\4\0\5\4\5\0\5\4"+
    "\1\161\16\4\13\0\2\4\4\0\5\4\5\0\1\4"+
    "\1\162\22\4\13\0\2\4\4\0\5\4\5\0\12\4"+
    "\1\163\11\4\12\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4042];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\7\1\2\11\12\1\12\11\4\1\1\11"+
    "\4\1\1\0\40\1\1\0\13\1\1\11\34\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  public _JdlLexer() {
    this((java.io.Reader)null);
  }

  private final IntArrayList myStateStack = new IntArrayList();

  protected void resetInternal() {
    myStateStack.clear();
  }

  private void pushState(int newState) {
    myStateStack.add(yystate());
    yybegin(newState);
  }

  private void popState() {
    if (myStateStack.isEmpty()) return;

    int state = myStateStack.removeInt(myStateStack.size() - 1);
    yybegin(state);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _JdlLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
        resetInternal();

    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        zzDoEOF();
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { if (myStateStack.isEmpty()) {
      return BAD_CHARACTER;
    }

    yypushback(1);
    popState();
            } 
            // fall through
          case 35: break;
          case 2: 
            { return IDENTIFIER;
            } 
            // fall through
          case 36: break;
          case 3: 
            { return NEWLINE;
            } 
            // fall through
          case 37: break;
          case 4: 
            { return DOUBLE_QUOTED_STRING;
            } 
            // fall through
          case 38: break;
          case 5: 
            { return REGEX_STRING;
            } 
            // fall through
          case 39: break;
          case 6: 
            { return INTEGER_NUMBER;
            } 
            // fall through
          case 40: break;
          case 7: 
            { return WILDCARD;
            } 
            // fall through
          case 41: break;
          case 8: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 42: break;
          case 9: 
            { return COMMA;
            } 
            // fall through
          case 43: break;
          case 10: 
            { return COLON;
            } 
            // fall through
          case 44: break;
          case 11: 
            { return LBRACKET;
            } 
            // fall through
          case 45: break;
          case 12: 
            { return RBRACKET;
            } 
            // fall through
          case 46: break;
          case 13: 
            { return LPARENTH;
            } 
            // fall through
          case 47: break;
          case 14: 
            { return RPARENTH;
            } 
            // fall through
          case 48: break;
          case 15: 
            { pushState(BRACES_BODY); return LBRACE;
            } 
            // fall through
          case 49: break;
          case 16: 
            { popState(); return RBRACE;
            } 
            // fall through
          case 50: break;
          case 17: 
            { return STRUDEL;
            } 
            // fall through
          case 51: break;
          case 18: 
            { return ASSIGN;
            } 
            // fall through
          case 52: break;
          case 19: 
            { return LINE_COMMENT;
            } 
            // fall through
          case 53: break;
          case 20: 
            { return BLOCK_COMMENT;
            } 
            // fall through
          case 54: break;
          case 21: 
            { return DOUBLE_NUMBER;
            } 
            // fall through
          case 55: break;
          case 22: 
            { return TO_KEYWORD;
            } 
            // fall through
          case 56: break;
          case 23: 
            { return USE_KEYWORD;
            } 
            // fall through
          case 57: break;
          case 24: 
            { return FOR_KEYWORD;
            } 
            // fall through
          case 58: break;
          case 25: 
            { return ENUM_KEYWORD;
            } 
            // fall through
          case 59: break;
          case 26: 
            { return WITH_KEYWORD;
            } 
            // fall through
          case 60: break;
          case 27: 
            { return TRUE;
            } 
            // fall through
          case 61: break;
          case 28: 
            { return FALSE;
            } 
            // fall through
          case 62: break;
          case 29: 
            { return ENTITY_KEYWORD;
            } 
            // fall through
          case 63: break;
          case 30: 
            { return EXCEPT_KEYWORD;
            } 
            // fall through
          case 64: break;
          case 31: 
            { return CONFIG_KEYWORD;
            } 
            // fall through
          case 65: break;
          case 32: 
            { return DEPLOYMENT_KEYWORD;
            } 
            // fall through
          case 66: break;
          case 33: 
            { return APPLICATION_KEYWORD;
            } 
            // fall through
          case 67: break;
          case 34: 
            { return RELATIONSHIP_KEYWORD;
            } 
            // fall through
          case 68: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
