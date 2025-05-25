package pl.grzeslowski.jsupla.generator.parser;

import java.util.List;
import java.util.SortedSet;

public record ParsedFile(List<Define> defines, SortedSet<Struct> structs) {
}
