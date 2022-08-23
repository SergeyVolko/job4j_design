package ru.job4j.asserj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 3);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisVerticesTetrahedron() {
        Box box = new Box(4, 3);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isPositive()
                .isBetween(2, 5)
                .isEqualTo(4);
    }

    @Test
    void isThisVerticesUnknown() {
        Box box = new Box(4, 0);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isNegative()
                .isEqualTo(-1);
    }

    @Test
    void isThisExist() {
        Box box = new Box(8, 2);
        boolean isExist = box.isExist();
        assertThat(isExist)
                .isNotNull()
                .isTrue()
                .isEqualTo(true);
    }

    @Test
    void isThisNotExist() {
        Box box = new Box(8, 0);
        boolean isNotExist = box.isExist();
        assertThat(isNotExist)
                .isNotNull()
                .isFalse()
                .isEqualTo(false);
    }

    @Test
    void isAreaEqualSphere() {
        Box box = new Box(0, 1);
        double area = box.getArea();
        assertThat(area)
                .isGreaterThan(12.56d)
                .isLessThan(12.57)
                .isCloseTo(12.56d, Percentage.withPercentage(1.0));
    }

    @Test
    void isAreaEqualUnknown() {
        Box box = new Box(2, 1);
        double area = box.getArea();
        assertThat(area)
                .isEqualTo(0d, withPrecision(0.0000001d))
                .isZero();
    }
}