package cn.mldn.zwb.util.web.exception.action;

import cn.mldn.zwb.util.web.exception.DispatcherException;

@SuppressWarnings("serial")
public class SplitUrlException extends DispatcherException {

	public SplitUrlException(String msg) {
		super(msg);
	}

}
