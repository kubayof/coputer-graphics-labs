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
            case 'k':
            case 'K':
                return new SymbolK(scale, painter);
            case 'u':
            case 'U':
                return new SymbolU(scale, painter);
            case 'b':
            case 'B':
                return new SymbolB(scale, painter);
            case 'a':
            case 'A':
                return new SymbolA(scale, painter);
            case 'i':
            case 'I':
                return new SymbolI(scale, painter);
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

    public static class SymbolU extends Symbol {
        protected SymbolU(int scale, Painter painter) {
            super(scale, painter);
        }

        @Override
        public void draw(int xTopLeft, int yTopLeft) {
            line(xTopLeft, yTopLeft, xTopLeft, yTopLeft + 7*scale);
            line(xTopLeft, yTopLeft + 7*scale,xTopLeft + scale, yTopLeft + 8*scale);
            line(xTopLeft + scale, yTopLeft + 8*scale, xTopLeft + 5*scale, yTopLeft + 8*scale);
            line(xTopLeft + 4*scale, yTopLeft + 8*scale, xTopLeft + 4*scale, yTopLeft);
        }
    }

    public static class SymbolB extends Symbol {
        protected SymbolB(int scale, Painter painter) {
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
            line(xTopLeft + 3*scale, yTopLeft + 4*scale, xTopLeft + 4*scale, yTopLeft + 5*scale);
            line(xTopLeft + 4*scale, yTopLeft + 5*scale,xTopLeft + 4*scale, yTopLeft + 7*scale);
            line(xTopLeft + 4*scale, yTopLeft + 7*scale, xTopLeft + 3*scale, yTopLeft + 8*scale);
            line(xTopLeft + 3*scale, yTopLeft + 8*scale,  xTopLeft, yTopLeft + 8*scale);
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
}
