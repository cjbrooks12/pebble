package com.mitchellbosecke.pebble.utils;

import java.util.HashMap;

public class Context extends HashMap<String, Object> {

	private static final long serialVersionUID = -7044995933897288856L;

	private final boolean strictVariables;

	private Context parent;

	public Context(boolean strictVariables) {
		this.strictVariables = strictVariables;
	}

	@Override
	public Object get(Object key) {
		if (!super.containsKey(key)) {
			if (parent != null) {
				return parent.get(key);
			} else {
				if (isStrictVariables()) {
					throw new RuntimeException(String.format(
							"Variable [%s] does not exist and strict variables is set to true.", String.valueOf(key)));
				}
			}
		}
		return super.get(key);
	}

	public Context getParent() {
		return parent;
	}

	public void setParent(Context parent) {
		this.parent = parent;
	}

	public boolean isStrictVariables() {
		return strictVariables;
	}

}
