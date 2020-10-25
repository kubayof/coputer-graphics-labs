package com.naofi.text;

import com.naofi.commons.Painter;

public abstract class Symbol {
    protected int scale = 10;
    protected final Painter painter;

    protected Symbol(int scale, Painter painter) {
        this.scale = scale;
        this.painter = painter;
    }

    public static Symbol of(char character, int scale, Painter painter) {
        switch (character) {
            case 'r':
            case 'R':
                return new SymbolR(scale, painter);
            case 'n':
            case 'N':
                return new SymbolN(scale, painter);
            case 'd':
            case 'D':
                return new SymbolD(scale, painter);
            case 'a':
            case 'A':
                return new SymbolA(scale, painter);
            case 'i':
            case 'I':
                return new SymbolI(scale, painter);
            case 'y':
            case 'Y':
                return new SymbolY(scale, painter);
            case 'G':
            case 'g':
                return new SymbolG(scale, painter);
            case 'O':
            case 'o':
                return new SymbolO(scale, painter);
            case 'L':
            case 'l':
                return new SymbolL(scale, painter);
            case 'v':
            case 'V':
                return new SymbolV(scale, painter);
            case 's':
            case 'S':
                return new SymbolS(scale, painter);
            case 'k':
            case 'K':
                return new SymbolK(scale, painter);
            default:
                throw new IllegalArgumentException(
                        String.format("Symbol %c is not supported", character)
                );
        }
    }

    protected void line(int x1, int y1, int x2, int y2) {
        painter.bresenhamLine(x1, y1, x2, y2);
    }

    public abstract void draw(int xTopLeft, int yTopLeft);

    public static class SymbolN extends Symbol {
        protected SymbolN(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft, xTopLeft, yTopLeft + 8*scale);
            line(xTopLeft, yTopLeft + 8*scale, xTopLeft + 4*scale, yTopLeft);
            line(xTopLeft + 4 * scale, yTopLeft, xTopLeft + 4*scale, yTopLeft + 8*scale);
        }
    }

    public static class SymbolD extends Symbol {
        protected SymbolD(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft, xTopLeft, yTopLeft + 8*scale);
            line(xTopLeft, yTopLeft,xTopLeft + 4*scale, yTopLeft);
            line(xTopLeft + 4*scale, yTopLeft, xTopLeft + 5*scale, yTopLeft + scale);
            line(xTopLeft + 5*scale, yTopLeft + scale, xTopLeft + 5*scale, yTopLeft + 7*scale);
            line(xTopLeft + 5*scale, yTopLeft + 7*scale, xTopLeft + 4*scale, yTopLeft +8*scale);
            line(xTopLeft + 4*scale, yTopLeft + 8*scale, xTopLeft, yTopLeft + 8*scale);
        }
    }

    public static class SymbolR extends Symbol {
        protected SymbolR(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft, xTopLeft, yTopLeft + 8*scale);
            line(xTopLeft, yTopLeft, xTopLeft + 2*scale, yTopLeft);
            line(xTopLeft + 2*scale, yTopLeft,xTopLeft + 3*scale, yTopLeft + scale);
            line(xTopLeft + 3*scale, yTopLeft + scale, xTopLeft + 3*scale, yTopLeft + 3*scale);
            line(xTopLeft + 3*scale, yTopLeft + 3*scale, xTopLeft + 2*scale, yTopLeft + 4*scale);
            line(xTopLeft, yTopLeft + 4*scale, xTopLeft + 3*scale, yTopLeft + 4*scale);
            line(xTopLeft + 3*scale, yTopLeft + 4*scale, xTopLeft + 4*scale, yTopLeft + 8*scale);
        }
    }

    public static class SymbolA extends Symbol {
        protected SymbolA(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft + 8*scale, xTopLeft + 2*scale, yTopLeft);
            line(xTopLeft + 2*scale, yTopLeft, xTopLeft + 4*scale, yTopLeft + 8*scale);
            line(xTopLeft + scale, yTopLeft + 4*scale,xTopLeft + 3*scale, yTopLeft + 4*scale);
        }
    }

    public static class SymbolI extends Symbol {
        protected SymbolI(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft + 2*scale, yTopLeft, xTopLeft + 2*scale, yTopLeft + 8*scale);
        }
    }

    public static class SymbolY extends Symbol {
        protected SymbolY(int scale, Painter painter) {super(scale, painter); }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft, xTopLeft + 2*scale, yTopLeft + 2*scale);
            line(xTopLeft + 2*scale, yTopLeft + 2*scale, xTopLeft + 4*scale, yTopLeft);
            line(xTopLeft + 2*scale, yTopLeft + 2*scale, xTopLeft + 2*scale, yTopLeft + 8*scale);
        }
    }
    public  static class SymbolG extends Symbol {
        protected SymbolG(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft + 5*scale, yTopLeft + 1*scale, xTopLeft + 4*scale, yTopLeft);
            line(xTopLeft + 4*scale, yTopLeft, xTopLeft + 2*scale, yTopLeft);
            line(xTopLeft + 2*scale, yTopLeft, xTopLeft, yTopLeft + 2*scale);
            line(xTopLeft, yTopLeft + 2*scale, xTopLeft, yTopLeft + 6*scale);
            line(xTopLeft, yTopLeft + 6*scale, xTopLeft + 2*scale, yTopLeft + 8*scale);
            line(xTopLeft + 2*scale, yTopLeft + 8*scale, xTopLeft + 5*scale, yTopLeft + 8*scale);
            line(xTopLeft + 5*scale, yTopLeft + 8*scale, xTopLeft + 5*scale, yTopLeft + 5*scale);
            line(xTopLeft + 5*scale, yTopLeft + 5*scale, xTopLeft + 2*scale, yTopLeft + 5*scale);

        }
    }
    public static class SymbolO extends Symbol {
        protected SymbolO(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int  yTopLeft){
            line(xTopLeft, yTopLeft + scale, xTopLeft + scale, yTopLeft);
            line(xTopLeft + scale, yTopLeft, xTopLeft + 4*scale, yTopLeft);
            line(xTopLeft + 4*scale, yTopLeft, xTopLeft + 5*scale, yTopLeft + scale);
            line(xTopLeft +5*scale, yTopLeft + scale, xTopLeft + 5*scale, yTopLeft + 7*scale);
            line(xTopLeft + 5*scale, yTopLeft + 7*scale, xTopLeft + 4*scale, yTopLeft + 8*scale);
            line(xTopLeft + 4*scale, yTopLeft + 8*scale, xTopLeft + scale, yTopLeft + 8*scale);
            line(xTopLeft + scale, yTopLeft + 8*scale, xTopLeft, yTopLeft + 7*scale);
            line(xTopLeft, yTopLeft + 7*scale, xTopLeft, yTopLeft + scale);
        }
    }
    public static class SymbolL extends  Symbol {
        protected  SymbolL(int scale, Painter painter) {
            super(scale, painter);}

        @Override
        public void draw(int xTopLeft, int yTopLeft){
            line(xTopLeft, yTopLeft, xTopLeft, yTopLeft + 8*scale);
            line(xTopLeft, yTopLeft + 8*scale, xTopLeft + 5*scale, yTopLeft + 8*scale);
        }
    }
    public  static class SymbolV extends Symbol {
        protected SymbolV(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft, xTopLeft + 2 * scale, yTopLeft + 8 * scale);
            line(xTopLeft + 2 * scale, yTopLeft + 8 * scale, xTopLeft + 4 * scale, yTopLeft);
        }
    }
    public static class SymbolK extends Symbol {
        protected SymbolK(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft, xTopLeft, yTopLeft + 8*scale);
            line(xTopLeft, yTopLeft + 4*scale, xTopLeft + 4*scale, yTopLeft);
            line(xTopLeft, yTopLeft + 4*scale, xTopLeft + 4*scale, yTopLeft + 8*scale);
        }
    }
    public static class SymbolS extends Symbol {
        protected SymbolS(int scale, Painter painter) { super(scale, painter);}

        @Override
        public  void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft + 5*scale, yTopLeft + scale, xTopLeft + 4*scale, yTopLeft);
            line(xTopLeft + 4*scale, yTopLeft, xTopLeft + 2*scale, yTopLeft);
            line(xTopLeft + 2*scale, yTopLeft, xTopLeft, yTopLeft + 2*scale);
            line(xTopLeft, yTopLeft + 2*scale, xTopLeft, yTopLeft + 4*scale);
            line(xTopLeft, yTopLeft + 4*scale, xTopLeft + 4*scale, yTopLeft + 4*scale);
            line(xTopLeft + 4*scale, yTopLeft + 4*scale, xTopLeft + 5*scale, yTopLeft + 5*scale);
            line(xTopLeft + 5*scale, yTopLeft + 5*scale, xTopLeft + 5*scale, yTopLeft + 7*scale);
            line(xTopLeft + 5*scale, yTopLeft + 7*scale, xTopLeft + 4*scale, yTopLeft + 8*scale);
            line(xTopLeft + 4*scale, yTopLeft + 8*scale, xTopLeft + scale, yTopLeft + 8*scale);
            line(xTopLeft + scale, yTopLeft + 8*scale, xTopLeft, yTopLeft + 7*scale);
        }
    }
}