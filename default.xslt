<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:include href="stylesheets/iri.xslt"/>

<xsl:template match="/ROOT/COREF"/>

<xsl:template match="/ROOT/*" priority="0">
	Unknown(
	<xsl:for-each select=".//*[@id]">
		<xsl:value-of select="."/><xsl:text> </xsl:text>
    </xsl:for-each>)
</xsl:template>

<xsl:template match="/ROOT/S[count(S)>0]">
    <xsl:for-each select="S">
        <xsl:apply-templates select="."/>
    </xsl:for-each>
</xsl:template>

<xsl:include href="stylesheets/axioms/equivalentClasses.xslt"/>
<xsl:include href="stylesheets/axioms/classAssertion.xslt"/>
<xsl:include href="stylesheets/axioms/objectPropertyAssertion.xslt"/>
<xsl:include href="stylesheets/axioms/objectPropertyDomainAndRange.xslt"/>
<xsl:include href="stylesheets/axioms/subClassOf.xslt"/>
<xsl:include href="stylesheets/axioms/disjointClasses.xslt"/>

<!-- queries -->

<xsl:include href="stylesheets/queries/isDirectSubClassOf.xslt"/>
<xsl:include href="stylesheets/queries/isInstanceOf.xslt"/>
<xsl:include href="stylesheets/queries/getObjectPropertyValues.xslt"/>
<xsl:include href="stylesheets/queries/getInstances.xslt"/>


<!-- phrases -->

<xsl:include href="stylesheets/expressions/objectIntersectionOf.xslt"/>
<xsl:include href="stylesheets/expressions/objectUnionOf.xslt"/>
<xsl:include href="stylesheets/expressions/objectSomeValuesFrom.xslt"/>
<xsl:include href="stylesheets/expressions/objectMinCardinality.xslt"/>

<xsl:template match="NP[count(SBAR)=0 and count(NP)=0 and count(CC)=0]">
   <xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="." />
	</xsl:call-template>
</xsl:template>
<xsl:template match="NNS" priority="0">
   <xsl:call-template name="noun_to_class">
	<xsl:with-param name="noun" select="." />
    </xsl:call-template>
</xsl:template>
<xsl:template match="NN[@entity]|NNP" priority="0">
   <xsl:call-template name="noun_to_individual">
	<xsl:with-param name="noun" select="." />
    </xsl:call-template>
</xsl:template>
<xsl:template match="NP[.//NNP|.//*[@entity]]" priority="0">
   <xsl:call-template name="noun_phrase_to_individual">
        <xsl:with-param name="NP" select="." />
   </xsl:call-template>
</xsl:template>
 
<xsl:template match="text()|@*"/>

</xsl:stylesheet>