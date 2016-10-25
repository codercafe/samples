package io.github.codercafe.samples.testing;

import java.net.URL;
import java.util.Objects;

public class SoftwareEngineer {

    public final String name;
    public final long phone;
    public final URL gitHub;

    public SoftwareEngineer(String name, long phone) {
        this(name, phone, null);
    }

    public SoftwareEngineer(String name, long phone, URL gitHub) {
        this.name = name;
        this.phone = phone;
        this.gitHub = gitHub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SoftwareEngineer that = (SoftwareEngineer) o;
        return phone == that.phone &&
                Objects.equals(name, that.name) &&
                Objects.equals(gitHub, that.gitHub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, gitHub);
    }
}