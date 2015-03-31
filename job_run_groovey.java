@Override
	public PerformResult perform(final CustomCronJobModel cronJob)
	{

        String groovyScript=cronJob.getGroovyScript(); // the cronjobModel has to be extended to allow persistence of the groovey script
	    final CustomGroovyShell shell = new CustomGroovyShell();
		final Map<String, Object> ctx = new HashMap<String, Object>();
		final ApplicationContext springCtx = Registry.getApplicationContext();
		ctx.put("ctx", springCtx); // expose the spring context to your groovy scrip to allow getting spring bean
		ctx.put("item", item); // expose Model so it can be used direcly 
		ctx.put("attributeQualifier", attributeQualifier);
		ctx.put("locale", locale);
		final Object eval = shell.eval(ctx, groovyScript);
		return eval != null ? String.valueOf(eval) : null;

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
